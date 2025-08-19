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
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

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
            log.info("开始上传文件: {}, {}, {}, {}", file.getOriginalFilename(), key, ossProperties.getBucket(), file.getContentType());
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            log.info("文件上传成功: {}", key);
            return getFileUrl(key);
        } catch (IOException e) {
            log.error("文件上传失败: {}", key);
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
}