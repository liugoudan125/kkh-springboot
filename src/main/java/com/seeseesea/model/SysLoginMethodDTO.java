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
 * (SysLoginMethod)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-03 11:21:16
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysLoginMethodDTO {

    private String id;

    /**
     * 用户ID
     **/
    private String userId;

    /**
     * 登录方式('email','username','github','wechat','gitee'）
     **/
    private String methodType;

    /**
     * 邮箱、用户名、第三方平台用户ID
     **/
    private String identifier;

    /**
     * 密码，access_token
     **/
    private String accessToken;

    /**
     * 如果第三方平台登录，令牌的过期时间
     **/
    private LocalDateTime expiresAt;

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 更新时间
     **/
    private LocalDateTime updatedAt;

}

