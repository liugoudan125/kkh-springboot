package com.seeseesea.model.dto;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * 文章分类表(Category)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:33:31
 */
@Data
public class CategoryDTO {

    /**
     * 主键ID
     **/
    private Long id;

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

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     **/
    private LocalDateTime updatedAt;

}

