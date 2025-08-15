package com.seeseesea.model.dto;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:33:31
 */
@Data
public class ArticleTagDTO {

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

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

}

