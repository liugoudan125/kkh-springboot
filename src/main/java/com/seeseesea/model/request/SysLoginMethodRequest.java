package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 登录方式表(SysLoginMethod)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:38:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysLoginMethodRequest extends PageParams {

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
}

