package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seeseesea.model.SysFile;
import com.seeseesea.model.request.SysFileRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * (SysFile)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-03 11:20:12
 */
@Mapper
public interface SysFileDao extends BaseMapper<SysFile> {

    /**
     * 分页查询文件列表
     *
     * @param request 查询条件
     * @return 分页结果
     */
    default IPage<SysFile> page(SysFileRequest request) {
        return new LambdaQueryChainWrapper<>(this)
                .eq(Objects.nonNull(request.getId()), SysFile::getId, request.getId())
                .like(StringUtils.hasText(request.getFileName()), SysFile::getFileName, request.getFileName())
                .eq(StringUtils.hasText(request.getMimeType()), SysFile::getMimeType, request.getMimeType())
                .eq(Objects.nonNull(request.getUploadUserId()), SysFile::getUploadUserId, request.getUploadUserId())
                .eq(Objects.nonNull(request.getFileSize()), SysFile::getFileSize, request.getFileSize())
                .eq(Objects.nonNull(request.getUploadAt()), SysFile::getUploadAt, request.getUploadAt())
                .orderByDesc(SysFile::getCreatedAt)
                .page(Page.of(request.getCurrent(), request.getPageSize()));
    }


    default SysFile selectByDigestAndSize(String fileDigest, long fileSize) {
        return new LambdaQueryChainWrapper<>(this)
                .eq(SysFile::getFileDigest, fileDigest)
                .eq(SysFile::getFileSize, fileSize)
                .last("LIMIT 1")
                .one();
    }
}

