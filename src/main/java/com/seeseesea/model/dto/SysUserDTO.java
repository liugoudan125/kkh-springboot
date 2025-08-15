package com.seeseesea.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


/**
 * (SysUser)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-03 11:21:16
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysUserDTO implements Authentication {

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

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 更新时间
     **/
    private LocalDateTime updatedAt;

    /**
     * 角色列表
     */
    private List<SysRoleDTO> authorities;

    private boolean authenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    @JsonIgnore
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return nickname;
    }
}

