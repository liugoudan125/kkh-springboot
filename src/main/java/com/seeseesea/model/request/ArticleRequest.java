package com.seeseesea.model.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * 文章表(Article)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArticleRequest {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 文章标题
     **/
    private String title;
    /**
     * 文章内容（Markdown格式）
     **/
    private String content;
    /**
     * 文章摘要
     **/
    private String summary;
    /**
     * 封面图片URL
     **/
    private String coverImage;
    /**
     * 作者ID
     **/
    private String authorId;
    /**
     * 分类ID
     **/
    private String categoryId;
    /**
     * 专栏ID
     **/
    private String seriesId;
    /**
     * 在专栏中的排序
     **/
    private Integer seriesOrder;
    /**
     * 浏览次数
     **/
    private Long viewCount;
    /**
     * 点赞次数
     **/
    private Long likeCount;
    /**
     * 评论次数
     **/
    private Long commentCount;
    /**
     * 状态：draft-草稿，published-已发布，archived-已归档
     **/
    private String status;
    /**
     * 是否置顶：0-否，1-是
     **/
    private Integer isTop;
    /**
     * 是否允许评论：0-否，1-是
     **/
    private Integer allowComment;
    /**
     * 发布时间
     **/
    private LocalDateTime publishedAt;
}

