package com.seeseesea.core.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.seeseesea.core.utils.SnowflakeIdGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MybatisPlusConfig
 */
@Configuration
//@MapperScan(basePackages = "com.seeseesea")
public class MybatisPlusConfig {

    private static final Logger log = LoggerFactory.getLogger(MybatisPlusConfig.class);

    /**
     * 添加分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        log.info("{}Mybatis-plus 插件加载开始{}", "=".repeat(20), "=".repeat(20));
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor()); //防全表更新与删除插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//如果配置多个插件,切记分页最后添加
        log.info("{}Mybatis-plus 插件加载完成{}", "=".repeat(20), "=".repeat(20));

        return interceptor;
    }

    /**
     * mybatis-plus公共字段自动填充
     * 尽量使用应用服务的时间戳,防止和mysql服务器时间不一致
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
                this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
                //如果updateTime已经有值,就不重新填充
                //this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

            }
        };
    }

    @Bean
    public IdentifierGenerator identifierGenerator(SnowflakeIdGenerator idGenerator) throws UnknownHostException {
        return new IdentifierGenerator() {

            @Override
            public Number nextId(Object entity) {
                return idGenerator.nextId();
            }

        };
    }

    static ExecutorService executorService = Executors.newFixedThreadPool(16);

}
