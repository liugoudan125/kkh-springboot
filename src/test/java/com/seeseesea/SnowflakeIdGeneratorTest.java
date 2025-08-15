package com.seeseesea;

import com.seeseesea.core.utils.SnowflakeIdGenerator;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;

/**
 * snowflakeIdGeneratorTest
 */
@SpringBootTest(classes = Application.class)
public class SnowflakeIdGeneratorTest {

    private static final Logger log = LoggerFactory.getLogger(SnowflakeIdGeneratorTest.class);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() throws InterruptedException {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(redisTemplate);
        for (int i = 0; i < 10; i++) {
            extracted(snowflakeIdGenerator);
        }
    }

    private void extracted(SnowflakeIdGenerator snowflakeIdGenerator) throws InterruptedException {
        int count = 1000000;
        long[] ids = new long[count];
        long s = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            ids[i] = snowflakeIdGenerator.nextId();
        }
        log.info("耗时: {}", System.currentTimeMillis() - s);
        for (int i = 0; i < 3; i++) {
            int index = new Random().nextInt(count);
            log.info("进行检查: {}, {}", index, ids[index]);
        }
    }
}
