package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章专栏表(Series)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:38:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SeriesRequest extends PageParams {

    /**
     * 主键ID
     **/
    private Long id;
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

