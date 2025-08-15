package com.seeseesea.model.dto;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * 登录方式表(SysLoginMethod)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:33:32
 */
@Data
public class SysLoginMethodDTO {

    private Long id;

    /**
     * 用户ID
     **/
    private Long userId;

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

