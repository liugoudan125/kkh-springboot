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
 * 相册照片表(AlbumPhoto)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class AlbumPhoto {

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

