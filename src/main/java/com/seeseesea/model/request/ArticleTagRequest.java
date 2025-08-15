package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:38:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleTagRequest extends PageParams {

    /**
     * 主键ID
     **/
    private Long id;
    /**
     * 文章ID
     **/
    private Long articleId;
    /**
     * 标签ID
     **/
    private Long tagId;
}

