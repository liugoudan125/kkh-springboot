package com.seeseesea.oss;


import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;

/**
 * S3Test
 */
public class S3Test {

    public static void main(String[] args) {
        AwsBasicCredentials credentials = AwsBasicCredentials.builder()
                .accessKeyId("kkh")
                .secretAccessKey("123456")
                .build();
        S3Client s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(URI.create("https://oss.seeseesea.com"))
                .forcePathStyle(true)
                .region(Region.AF_SOUTH_1)
                .build();
        ResponseInputStream<GetObjectResponse> inputStream = s3Client.getObject(GetObjectRequest.builder()
                .bucket("kkh")
                .key("dev/20e94129edb84811ad6252ec1c457c3b.png")
                .build());
        try {
            inputStream.transferTo(new FileOutputStream("aa.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            s3Client.close();
        }
    }
}
