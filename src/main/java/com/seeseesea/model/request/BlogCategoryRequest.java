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
 * 文章分类表(BlogCategory)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-17 22:58:30
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogCategoryRequest {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 分类名称
     **/
    private String name;
    /**
     * 分类描述
     **/
    private String description;
    /**
     * 分类图标
     **/
    private String icon;
    /**
     * 排序权重
     **/
    private Integer sortOrder;
    /**
     * 状态：active-启用，inactive-禁用
     **/
    private String status;
}

