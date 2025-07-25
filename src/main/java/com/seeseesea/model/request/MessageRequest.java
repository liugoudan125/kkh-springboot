package com.seeseesea.model.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 留言表(Message)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:57
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MessageRequest {

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
}

