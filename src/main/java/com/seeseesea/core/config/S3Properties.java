package com.seeseesea.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * S3配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss.s3")
public class S3Properties {

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 秘密密钥
     */
    private String secretKey;

    /**
     * 区域
     */
    private String region = "us-east-1";

    /**
     * 自定义端点（用于MinIO等兼容S3的服务）
     */
    private String endpoint;

    /**
     * 存储桶名称
     */
    private String bucket;

    /**
     * 存储路径
     */
    private String path;

    private Boolean forcePathStyle = true;
}