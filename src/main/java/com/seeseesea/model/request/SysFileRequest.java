package com.seeseesea.model.request;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * (SysFile)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-17 22:58:30
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysFileRequest {

    private String id;
    /**
     * 文件原始名称
     **/
    private String fileName;
    /**
     * 文件大小（字节）
     **/
    private Long fileSize;
    /**
     * 文件类型（如 image/jpeg,application/pdf）
     **/
    private String mimeType;
    /**
     * 在oss上的桶
     **/
    private String bucket;
    /**
     * 在oss上的key
     **/
    private String objectKey;
    /**
     * OSS 文件存储地址
     **/
    private String ossUrl;
    /**
     * 上传者用户ID
     **/
    private Long uploadUserId;
    /**
     * 文件上传时间
     **/
    private LocalDateTime uploadAt;
}

