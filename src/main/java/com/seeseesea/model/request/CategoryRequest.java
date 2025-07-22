package com.seeseesea.model.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 文章分类表(Category)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:56
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CategoryRequest {

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

