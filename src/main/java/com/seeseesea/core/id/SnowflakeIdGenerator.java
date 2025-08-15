package com.seeseesea.core.id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Snowflake ID 生成器，基于雪花算法实现唯一的分布式 ID 生成。
 * <p>
 * 该生成器结合了 Redis 租约机制来确保每个节点的唯一 ID，并通过定时续约保证租约有效性。每个节点由一个唯一的 `nodeId` 标识，该节点 ID 会在 Redis 中进行租约管理，确保在高并发环境下的唯一性和安全性。
 * <p>
 * 雪花算法生成的 ID 是 64 位的，格式如下：
 * - 1 位符号位
 * - 41 位时间戳（毫秒级）
 * - 10 位节点 ID（合并了 datacenterId 和 workId）
 * - 12 位序列号（同一毫秒内递增）
 * <p>
 * 支持时钟回拨的保护，回拨超出阈值时会自动跳过到下一个毫秒，确保不会抛出时钟回拨异常。
 * <p>
 * 主要特点：
 * - 使用 Redis 作为分布式环境下节点 ID 分配的存储，确保节点 ID 唯一。
 * - 每个节点通过 Redis 租约机制来保证在指定时间内租约有效，防止节点冲突。
 * - 采用同步锁（`synchronized`）来确保同一时刻只有一个线程生成 ID，避免并发问题。
 * - 在高并发场景下，通过时钟回拨的自动跳秒来防止时钟回拨带来的异常。
 * - ID 生成时，支持时钟回拨保护，并能够自动跳到下一个毫秒以避免生成重复的 ID。
 * <p>
 * 适用场景：
 * - 分布式系统中需要保证生成的 ID 唯一且高效。
 * - 大规模、高并发环境下的分布式 ID 生成需求，尤其是在使用 Redis 作为分布式存储时。
 * <p>
 * 注意事项：
 * - 必须配置 Redis 服务，且节点 ID 必须保证每个节点唯一。
 * - 时钟回拨的处理能力有限，建议配置正确的服务器时间，避免过大的时钟回拨。
 * <p>
 * 构造函数：
 * - `SnowflakeIdGenerator(RedisTemplate<String, Object> redisTemplate)`：初始化雪花算法生成器，传入 RedisTemplate 用于分配和续约节点 ID。
 * <p>
 * 核心方法：
 * - `nextId()`：生成一个新的唯一 ID。
 * - `parseId(long id)`：解析生成的 ID，返回时间戳、节点 ID 和序列号。
 * - `shutdown()`：关闭生成器，释放资源并删除 Redis 中的节点租约信息。
 *
 * @author liugoudan
 * @since 1.0
 */
@Component
public class SnowflakeIdGenerator extends AbstractSmartLifecycle {

    // ====================== 基础参数 ======================
    private static final long EPOCH = LocalDateTime.of(2020, 1, 1, 0, 0, 0)
            .atZone(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli();

    private static final long NODE_ID_BITS = 10L; // 节点ID位数
    private static final long SEQUENCE_BITS = 12L; // 序列号位数
    private static final long MAX_NODE_ID = ~(-1L << NODE_ID_BITS);

    private static final long NODE_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    // ====================== Redis 租约相关 ======================
    private static final String NODE_KEY_PREFIX = "kkh:snowflake:node:";
    private static final long LEASE_SECONDS = 70;       // 租约时长
    private static final long RENEW_INTERVAL_SECONDS = 60; // 续约周期
    private static final Logger log = LoggerFactory.getLogger(SnowflakeIdGenerator.class);

    private final RedisTemplate<String, Object> redisTemplate;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private volatile long nodeId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator(RedisTemplate<String, Object> redisTemplate) {
        log.info("{}雪花主键生成器初始化{}", "=".repeat(20), "=".repeat(20));
        this.redisTemplate = redisTemplate;
        this.nodeId = allocateNodeIdWithLease();
        scheduler.scheduleAtFixedRate(this::renewLease, RENEW_INTERVAL_SECONDS, RENEW_INTERVAL_SECONDS, TimeUnit.SECONDS);
        log.info("{}雪花主键生成器初始化完成{}", "=".repeat(20), "=".repeat(20));

    }


    // ====================== 核心方法 ======================
    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        // 时钟回拨保护,跳秒
        if (currentTimestamp < lastTimestamp) {
            long offset = lastTimestamp - currentTimestamp;
            if (offset <= 5) {
                try {
                    wait(offset); // 小回拨等待修正
                    currentTimestamp = System.currentTimeMillis();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("生成ID被中断", e);
                }
            } else {
                // 大回拨，跳到下一毫秒
                currentTimestamp = tilNextMillis(lastTimestamp); // 自动跳到下一个毫秒
                log.warn("时钟回拨过大，自动跳过：{} ms", offset);
            }
        }

        // 同一毫秒内
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - EPOCH) << TIMESTAMP_SHIFT) | (nodeId << NODE_ID_SHIFT) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long ts = System.currentTimeMillis();
        while (ts <= lastTimestamp) {
            ts = System.currentTimeMillis();
        }
        return ts;
    }

    // ====================== Redis 租约 ======================
    private long allocateNodeIdWithLease() {
        Random random = new Random();
        for (int i = 0; i <= MAX_NODE_ID; i++) {
            long candidateId = random.nextInt((int) MAX_NODE_ID + 1);
            String key = NODE_KEY_PREFIX + candidateId;
            Boolean success = null;
            try {
                success = redisTemplate.opsForValue().setIfAbsent(key, InetAddress.getLocalHost().getHostAddress(), LEASE_SECONDS, TimeUnit.SECONDS);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            if (Boolean.TRUE.equals(success)) {
                log.info("成功分配节点 ID: {}", candidateId);
                return candidateId;
            }
        }
        throw new RuntimeException("没有可用的节点 ID（1024 个已全部占用）");
    }

    private void renewLease() {
        String key = NODE_KEY_PREFIX + nodeId;
        if (!redisTemplate.expire(key, LEASE_SECONDS, TimeUnit.SECONDS)) {
            this.nodeId = allocateNodeIdWithLease();
        }
    }

    // ====================== ID 解析工具 ======================
    public static IdInfo parseId(long id) {
        long ts = (id >>> TIMESTAMP_SHIFT) + EPOCH;
        long nodeId = (id >>> NODE_ID_SHIFT) & MAX_NODE_ID;
        long seq = id & SEQUENCE_MASK;
        return new IdInfo(ts, nodeId, seq);
    }

    @Override
    public void destroy() {
        log.info("销毁: {}", this.getClass().getName());
        scheduler.shutdown();
        redisTemplate.delete(NODE_KEY_PREFIX + nodeId);
    }


    public record IdInfo(long timestamp, long nodeId, long sequence) {
    }
}
