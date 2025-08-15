package com.seeseesea.service.impl;

import com.seeseesea.core.properties.OssProperties;
import com.seeseesea.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.time.Duration;

/**
 * S3服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;
    private final OssProperties ossProperties;

    @Override
    public String uploadFile(MultipartFile file, String key) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            log.info("文件上传成功: {}", key);
            return getFileUrl(key);
        } catch (IOException e) {
            log.error("文件上传失败: {}", key, e);
            throw new RuntimeException("文件上传失败", e);
        }
    }

    @Override
    public void deleteFile(String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(ossProperties.getBucket())
                .key(key)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
        log.info("文件删除成功: {}", key);
    }

    @Override
    public String getFileUrl(String key) {
        if (StringUtils.isNotBlank(ossProperties.getCdnHost())) {
            return ossProperties.getCdnHost() + "/" + ossProperties.getBucket() + "/" + key;
        } else {
            return ossProperties.getEndpoint() + "/" + ossProperties.getBucket() + "/" + key;
        }
    }

    /**
     * 生成预签名URL
     *
     * @param key 文件键名
     * @return 预签名URL
     */
    private String generatePresignedUrl(String key) {
        try (S3Presigner presigner = S3Presigner.builder()
                .s3Client(s3Client)
                .build()) {

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(key)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofHours(1)) // URL有效期1小时
                    .getObjectRequest(getObjectRequest)
                    .build();

            PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
            return presignedRequest.url().toString();
        } catch (Exception e) {
            log.error("生成预签名URL失败: {}", key, e);
            throw new RuntimeException("生成预签名URL失败", e);
        }
    }
}