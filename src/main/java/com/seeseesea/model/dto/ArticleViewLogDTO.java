package com.seeseesea.model.dto;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * 文章浏览记录表(ArticleViewLog)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:33:32
 */
@Data
public class ArticleViewLogDTO {

    /**
     * 主键ID
     **/
    private Long id;

    /**
     * 文章ID
     **/
    private Long articleId;

    /**
     * IP地址
     **/
    private String ipAddress;

    /**
     * 用户代理
     **/
    private String userAgent;

    /**
     * 来源页面
     **/
    private String referer;

    /**
     * 浏览时间
     **/
    private LocalDateTime viewedAt;

}

