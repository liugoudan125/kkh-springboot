package com.seeseesea.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SnowflakeIdGenerator {

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
        this.redisTemplate = redisTemplate;
        this.nodeId = allocateNodeIdWithLease();
        scheduler.scheduleAtFixedRate(this::renewLease, RENEW_INTERVAL_SECONDS, RENEW_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    // ====================== 核心方法 ======================
    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        // 时钟回拨保护
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
                throw new RuntimeException("时钟回拨过大: " + offset + "ms");
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

        return ((currentTimestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (nodeId << NODE_ID_SHIFT)
                | sequence;
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
                success = redisTemplate.opsForValue()
                        .setIfAbsent(key, InetAddress.getLocalHost().getHostAddress(), LEASE_SECONDS, TimeUnit.SECONDS);
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

    public void shutdown() {
        scheduler.shutdown();
        redisTemplate.delete(NODE_KEY_PREFIX + nodeId);
    }

    // ====================== ID 解析工具 ======================
    public static IdInfo parseId(long id) {
        long ts = (id >>> TIMESTAMP_SHIFT) + EPOCH;
        long nodeId = (id >>> NODE_ID_SHIFT) & MAX_NODE_ID;
        long seq = id & SEQUENCE_MASK;
        return new IdInfo(ts, nodeId, seq);
    }

    public record IdInfo(long timestamp, long nodeId, long sequence) {
    }
}
