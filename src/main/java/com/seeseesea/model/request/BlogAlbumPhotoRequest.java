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
 * 相册照片表(BlogAlbumPhoto)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-17 22:58:29
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogAlbumPhotoRequest {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 相册ID
     **/
    private String albumId;
    /**
     * 照片标题
     **/
    private String title;
    /**
     * 照片描述
     **/
    private String description;
    /**
     * 图片URL
     **/
    private String imageUrl;
    /**
     * 缩略图URL
     **/
    private String thumbnailUrl;
    /**
     * 文件大小（字节）
     **/
    private Long fileSize;
    /**
     * 图片宽度
     **/
    private Integer width;
    /**
     * 图片高度
     **/
    private Integer height;
    /**
     * 排序权重
     **/
    private Integer sortOrder;
}

