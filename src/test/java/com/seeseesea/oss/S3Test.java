package com.seeseesea.oss;


import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.time.Duration;

/**
 * S3Test
 */
public class S3Test {

    public static void main(String[] args) {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                "aaa",
                "test1"
        );
        S3Client s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(URI.create("https://s3.cn-east-1.qiniucs.com"))
//                .endpointOverride(URI.create("https://oss.seeseesea.com"))
//                .forcePathStyle(true)
//                .region(Region.AF_SOUTH_1)
                .region(Region.of("cn-east-1"))
                .build();
        File file = new File("D:\\Downloads\\手机壁纸\\0927高级景深壁纸合集\\mmexport1723373470471.png");
        PutObjectResponse put = s3Client.putObject(
                PutObjectRequest.builder()
                        .key("dev/" + file.getName())
                        .bucket("kkh")
                        .build(),
                RequestBody.fromFile(file)
        );
        System.out.println(put.eTag());
    }
}
