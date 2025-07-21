package com.seeseesea.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.f4b6a3.uuid.UuidCreator;
import com.seeseesea.core.config.S3Properties;
import com.seeseesea.core.utils.BeanCopyUtils;
import com.seeseesea.dao.SysFileDao;
import com.seeseesea.model.SysFile;
import com.seeseesea.model.SysFileDTO;
import com.seeseesea.model.request.SysFileRequest;
import com.seeseesea.service.S3Service;
import com.seeseesea.service.SysFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * (SysFile)业务层接口实现类
 *
 * @author liuchenglong
 * @since 2025-07-02 22:25:41
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysFileServiceImpl implements SysFileService {

    private final S3Service s3Service;
    private final S3Properties s3Properties;
    private final SysFileDao sysFileDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<SysFileDTO> uploadFiles(List<MultipartFile> files, String uploadUserId) {
        try {
            List<SysFile> sysFiles = new ArrayList<>();
            for (MultipartFile file : files) {
                String originalFilename = file.getOriginalFilename();
                String key = getFileKey(originalFilename);
                String fileUrl = s3Service.uploadFile(file, key);
                log.info("上传成功，地址：{}", fileUrl);
                // 创建文件记录
                SysFile sysFile = new SysFile();
                sysFile.setFileName(originalFilename);
                sysFile.setFileSize(file.getSize());
                sysFile.setMimeType(file.getContentType());
                sysFile.setBucket(s3Properties.getBucket());
                sysFile.setObjectKey(key);
                sysFile.setOssUrl(fileUrl);
                sysFile.setUploadUserId(uploadUserId);
                sysFile.setUploadAt(LocalDateTime.now());
                sysFile.setCreatedAt(LocalDateTime.now());
                sysFile.setUpdatedAt(LocalDateTime.now());
                sysFiles.add(sysFile);
            }
            // 批量插入文件记录
            sysFileDao.insert(sysFiles);
            return BeanCopyUtils.copyList(sysFiles, SysFileDTO.class);
        } catch (Exception e) {
            log.error("上传文件失败,{}", files.stream().map(MultipartFile::getName).toList(), e);
            throw new RuntimeException("上传文件失败");
        }
    }

    @Override
    public Page<SysFileDTO> getFilePageList(SysFileRequest request, Long current, Long size) {
        // 创建分页对象
        Page<SysFile> page = new Page<>(current, size);

        // 查询分页数据
        Page<SysFile> resultPage = sysFileDao.selectFilePageList(page, request);

        // 转换为DTO
        return convertToPageDTO(resultPage);
    }

    @Override
    public Page<SysFileDTO> getFilePageListByCategory(SysFileRequest request, String fileCategory, Long current, Long size) {
        // 创建分页对象
        Page<SysFile> page = new Page<>(current, size);

        // 根据文件分类查询分页数据
        Page<SysFile> resultPage = sysFileDao.selectFilePageListByCategory(page, request, fileCategory);

        // 转换为DTO
        return convertToPageDTO(resultPage);
    }

    @Override
    public Page<SysFileDTO> getFilePageListByExtension(SysFileRequest request, String extension, Long current, Long size) {
        // 创建分页对象
        Page<SysFile> page = new Page<>(current, size);

        // 根据文件扩展名查询分页数据
        Page<SysFile> resultPage = sysFileDao.selectFilePageListByExtension(page, request, extension);

        // 转换为DTO
        return convertToPageDTO(resultPage);
    }

    @Override
    public SysFileDTO getFileById(String id) {
        SysFile sysFile = sysFileDao.selectById(id);
        if (Objects.isNull(sysFile)) {
            throw new RuntimeException("文件不存在，ID：" + id);
        }

        return BeanCopyUtils.copy(sysFile, SysFileDTO::new);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteFile(String id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("文件ID不能为空");
        }

        // 检查文件是否存在
        SysFile existingFile = sysFileDao.selectById(id);
        if (Objects.isNull(existingFile)) {
            throw new RuntimeException("文件不存在，ID：" + id);
        }
        s3Service.deleteFile(existingFile.getObjectKey());
        int result = sysFileDao.deleteById(id);
        return result > 0;
    }

    /**
     * 转换 Page<SysFile> 为 Page<SysFileDTO>
     *
     * @param resultPage 原始分页结果
     * @return DTO 分页结果
     */
    private Page<SysFileDTO> convertToPageDTO(Page<SysFile> resultPage) {
        Page<SysFileDTO> dtoPage = new Page<>();
        dtoPage.setCurrent(resultPage.getCurrent());
        dtoPage.setSize(resultPage.getSize());
        dtoPage.setTotal(resultPage.getTotal());
        dtoPage.setPages(resultPage.getPages());
        dtoPage.setRecords(BeanCopyUtils.copyList(resultPage.getRecords(), SysFileDTO.class));
        return dtoPage;
    }

    private String getFileKey(String originalFilename) {
        String extension = originalFilename != null ?
                originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        return s3Properties.getPath() + "/" + UuidCreator.getTimeOrderedEpoch().toString().replaceAll("-", "") + extension;
    }
}
