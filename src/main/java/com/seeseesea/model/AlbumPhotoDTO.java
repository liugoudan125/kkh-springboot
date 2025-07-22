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
public class AlbumPhotoDTO {

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
     * 排序权重
     **/
    private Integer sortOrder;

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     **/
    private LocalDateTime updatedAt;

}

