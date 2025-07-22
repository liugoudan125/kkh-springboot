package com.seeseesea.model.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArticleTagRequest {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 文章ID
     **/
    private String articleId;
    /**
     * 标签ID
     **/
    private String tagId;
}

