package com.seeseesea.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * S3Service
 */public interface S3Service {
    String uploadFile(MultipartFile file, String key);

    String uploadFile(InputStream inputStream, String key, String contentType);

    void deleteFile(String key);

    String getFileUrl(String key);
}
