package com.seeseesea.model;


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
 * 文章浏览记录表(ArticleViewLog)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:56
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArticleViewLogDTO {

    /**
     * 主键ID
     **/
    private String id;

    /**
     * 文章ID
     **/
    private String articleId;

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

