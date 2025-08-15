package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表(SysUser)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:38:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserRequest extends PageParams {

    /**
     * id
     **/
    private Long id;
    /**
     * 昵称（默认账户名）
     **/
    private String nickname;
    /**
     * 头像
     **/
    private String avatar;
    /**
     * 简介
     **/
    private String introduction;
}

