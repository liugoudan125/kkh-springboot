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
 * 文章专栏表(BlogSeries)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-17 22:58:30
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogSeriesRequest {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 专栏名称
     **/
    private String name;
    /**
     * 专栏描述
     **/
    private String description;
    /**
     * 封面图片URL
     **/
    private String coverImage;
    /**
     * 作者ID
     **/
    private Long authorId;
    /**
     * 文章数量
     **/
    private Integer articleCount;
    /**
     * 状态：active-启用，inactive-禁用
     **/
    private String status;
}

