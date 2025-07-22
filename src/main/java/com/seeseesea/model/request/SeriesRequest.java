package com.seeseesea.model.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 文章专栏表(Series)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:58
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SeriesRequest {

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

