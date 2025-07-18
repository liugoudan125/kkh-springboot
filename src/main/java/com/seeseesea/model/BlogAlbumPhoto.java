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
 * 相册照片表(BlogAlbumPhoto)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:27
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogAlbumPhoto {

    /**
     * 主键ID
     **/
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 相册ID
     **/
    @TableField(value = "album_id")
    private String albumId;

    /**
     * 照片标题
     **/
    @TableField(value = "title")
    private String title;

    /**
     * 照片描述
     **/
    @TableField(value = "description")
    private String description;

    /**
     * 图片URL
     **/
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 缩略图URL
     **/
    @TableField(value = "thumbnail_url")
    private String thumbnailUrl;

    /**
     * 文件大小（字节）
     **/
    @TableField(value = "file_size")
    private Long fileSize;

    /**
     * 图片宽度
     **/
    @TableField(value = "width")
    private Integer width;

    /**
     * 图片高度
     **/
    @TableField(value = "height")
    private Integer height;

    /**
     * 排序权重
     **/
    @TableField(value = "sort_order")
    private Integer sortOrder;

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

