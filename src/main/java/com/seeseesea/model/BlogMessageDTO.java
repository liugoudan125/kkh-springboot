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
 * 留言表(BlogMessage)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:29
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogMessageDTO {

    /**
     * 主键ID
     **/
    private String id;

    /**
     * 留言板ID
     **/
    private String boardId;

    /**
     * 用户ID（可为空，支持匿名留言）
     **/
    private String userId;

    /**
     * 留言者昵称
     **/
    private String nickname;

    /**
     * 留言者邮箱
     **/
    private String email;

    /**
     * 留言者网站
     **/
    private String website;

    /**
     * 留言者头像
     **/
    private String avatar;

    /**
     * 留言内容
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
     * 状态：pending-待审核，approved-已通过，rejected-已拒绝
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

