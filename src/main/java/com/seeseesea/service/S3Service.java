package com.seeseesea.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * S3Service
 */
public interface S3Service {
    String uploadFile(MultipartFile file, String key);

    void deleteFile(String key);

    String getFileUrl(String key);
}
