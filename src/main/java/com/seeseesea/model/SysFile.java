package com.seeseesea.model;


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
 * @since 2025-07-22 21:35:19
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysFile {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 文件原始名称
     **/
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 文件大小（字节）
     **/
    @TableField(value = "file_size")
    private Long fileSize;

    /**
     * 文件唯一标识（MD5或SHA256）
     **/
    @TableField(value = "file_digest")
    private String fileDigest;

    /**
     * 文件类型（如 image/jpeg,application/pdf）
     **/
    @TableField(value = "mime_type")
    private String mimeType;

    /**
     * 在oss上的桶
     **/
    @TableField(value = "bucket")
    private String bucket;

    /**
     * 在oss上的key
     **/
    @TableField(value = "object_key")
    private String objectKey;

    /**
     * OSS 文件存储地址
     **/
    @TableField(value = "oss_url")
    private String ossUrl;

    /**
     * 上传者用户ID
     **/
    @TableField(value = "upload_user_id")
    private String uploadUserId;

    /**
     * 文件上传时间
     **/
    @TableField(value = "upload_at")
    private LocalDateTime uploadAt;

    /**
     * 创建时间
     **/
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     **/
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

}

