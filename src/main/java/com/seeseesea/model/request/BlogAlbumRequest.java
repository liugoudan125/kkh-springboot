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
 * 相册表(BlogAlbum)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-17 22:58:29
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogAlbumRequest {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 相册名称
     **/
    private String name;
    /**
     * 相册描述
     **/
    private String description;
    /**
     * 封面图片URL
     **/
    private String coverImage;
    /**
     * 创建者ID
     **/
    private String authorId;
    /**
     * 照片数量
     **/
    private Integer photoCount;
    /**
     * 浏览次数
     **/
    private Long viewCount;
    /**
     * 状态：active-启用，inactive-禁用
     **/
    private String status;
}

