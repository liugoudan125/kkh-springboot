package com.seeseesea.core.config;


import com.seeseesea.core.properties.OssProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BeanConfig
 */
@Configuration
public class BeanConfig {

    @Bean(name = "executorService")
    public ExecutorService executorService() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    /**
     * S3Client的Bean
     */
    @Bean(name = "s3Client")
    public S3Client s3Client(OssProperties ossProperties) {

        // 创建认证信息
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(
                ossProperties.getAccessKey(),
                ossProperties.getSecretKey()
        );
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);

        // 构建S3Client
        return S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create(ossProperties.getEndpoint()))
                .forcePathStyle(ossProperties.getForcePathStyle())
                .build();
    }

}
