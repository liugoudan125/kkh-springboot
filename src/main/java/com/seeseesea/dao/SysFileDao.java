package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seeseesea.model.SysFile;
import com.seeseesea.model.request.SysFileRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.StringUtils;

import java.util.List;
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
     * @param page    分页参数
     * @param request 查询条件
     * @return 分页结果
     */
    default Page<SysFile> selectFilePageList(Page<SysFile> page, SysFileRequest request) {
        LambdaQueryWrapper<SysFile> queryWrapper = buildBaseQueryWrapper(request)
                // 按创建时间倒序排列
                .orderByDesc(SysFile::getCreatedAt);

        return selectPage(page, queryWrapper);
    }

    /**
     * 根据文件类型分类查询
     *
     * @param page         分页参数
     * @param request      基础查询条件
     * @param fileCategory 文件分类 (image/video/audio/document)
     * @return 分页结果
     */
    default Page<SysFile> selectFilePageListByCategory(Page<SysFile> page, SysFileRequest request, String fileCategory) {
        LambdaQueryWrapper<SysFile> queryWrapper = buildBaseQueryWrapper(request);

        // 根据文件分类添加条件
        if (StringUtils.hasText(fileCategory)) {
            switch (fileCategory.toLowerCase()) {
                case "image":
                    queryWrapper.and(wrapper -> wrapper
                            .likeRight(SysFile::getMimeType, "image/")
                            .or()
                            .apply("LOWER(SUBSTRING_INDEX(file_name, '.', -1)) IN ({0})",
                                    "'jpg','jpeg','png','gif','bmp','webp','ico','svg'"));
                    break;
                case "video":
                    queryWrapper.and(wrapper -> wrapper
                            .likeRight(SysFile::getMimeType, "video/")
                            .or()
                            .apply("LOWER(SUBSTRING_INDEX(file_name, '.', -1)) IN ({0})",
                                    "'mp4','avi','mov','wmv','flv','mkv','webm','m4v'"));
                    break;
                case "audio":
                    queryWrapper.and(wrapper -> wrapper
                            .likeRight(SysFile::getMimeType, "audio/")
                            .or()
                            .apply("LOWER(SUBSTRING_INDEX(file_name, '.', -1)) IN ({0})",
                                    "'mp3','wav','flac','aac','ogg','wma','m4a'"));
                    break;
                case "document":
                    queryWrapper.and(wrapper -> wrapper
                            .in(SysFile::getMimeType, List.of(
                                    "application/pdf", "application/msword",
                                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                                    "application/vnd.ms-excel",
                                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                                    "application/vnd.ms-powerpoint",
                                    "application/vnd.openxmlformats-officedocument.presentationml.presentation",
                                    "text/plain", "application/rtf"))
                            .or()
                            .apply("LOWER(SUBSTRING_INDEX(file_name, '.', -1)) IN ({0})",
                                    "'pdf','doc','docx','xls','xlsx','ppt','pptx','txt','rtf','json','md'"));
                    break;
            }
        }

        return selectPage(page, queryWrapper.orderByDesc(SysFile::getCreatedAt));
    }

    /**
     * 根据文件扩展名查询
     *
     * @param page      分页参数
     * @param request   基础查询条件
     * @param extension 文件扩展名
     * @return 分页结果
     */
    default Page<SysFile> selectFilePageListByExtension(Page<SysFile> page, SysFileRequest request, String extension) {
        LambdaQueryWrapper<SysFile> queryWrapper = buildBaseQueryWrapper(request)
                // 根据文件扩展名查询
                .apply(StringUtils.hasText(extension),
                        "LOWER(SUBSTRING_INDEX(file_name, '.', -1)) = LOWER({0})", extension)
                .orderByDesc(SysFile::getCreatedAt);

        return selectPage(page, queryWrapper);
    }

    /**
     * 构建基础查询条件
     *
     * @param request 查询请求
     * @return LambdaQueryWrapper
     */
    default LambdaQueryWrapper<SysFile> buildBaseQueryWrapper(SysFileRequest request) {
        return new LambdaQueryWrapper<SysFile>()
                .eq(Objects.nonNull(request.getId()), SysFile::getId, request.getId())
                .like(StringUtils.hasText(request.getFileName()), SysFile::getFileName, request.getFileName())
                .eq(StringUtils.hasText(request.getMimeType()), SysFile::getMimeType, request.getMimeType())
                .eq(Objects.nonNull(request.getUploadUserId()), SysFile::getUploadUserId, request.getUploadUserId())
                .eq(Objects.nonNull(request.getFileSize()), SysFile::getFileSize, request.getFileSize())
                .eq(Objects.nonNull(request.getUploadAt()), SysFile::getUploadAt, request.getUploadAt());
    }

    default SysFile selectByDigestAndSize(String fileDigest, long fileSize) {
        return new LambdaQueryChainWrapper<>(this)
                .eq(SysFile::getFileDigest, fileDigest)
                .eq(SysFile::getFileSize, fileSize)
                .last("LIMIT 1")
                .one();
    }
}

