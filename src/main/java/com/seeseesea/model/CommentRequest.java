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
 * 评论表(Comment)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:56
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CommentRequest {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 文章ID
     **/
    private String articleId;
    /**
     * 用户ID（可为空，支持匿名评论）
     **/
    private String userId;
    /**
     * 父评论ID（用于回复功能）
     **/
    private String parentId;
    /**
     * 评论者昵称
     **/
    private String nickname;
    /**
     * 评论者邮箱
     **/
    private String email;
    /**
     * 评论者网站
     **/
    private String website;
    /**
     * 评论者头像
     **/
    private String avatar;
    /**
     * 评论内容
     **/
    private String content;
    /**
     * IP地址
     **/
    private String ipAddress;
    /**
     * 用户代理
     **/
    private String userAgent;
    /**
     * 点赞次数
     **/
    private Integer likeCount;
    /**
     * 状态：pending-待审核，approved-已通过，rejected-已拒绝
     **/
    private String status;
}

