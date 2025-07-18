package com.seeseesea.core.config;


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
    public S3Client s3Client(S3Properties s3Properties) {

        // 创建认证信息
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(
                s3Properties.getAccessKey(),
                s3Properties.getSecretKey()
        );
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);

        // 构建S3Client
        return S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.of(s3Properties.getRegion()))
                .endpointOverride(URI.create(s3Properties.getEndpoint()))
                .forcePathStyle(s3Properties.getForcePathStyle()).build();
    }
}
