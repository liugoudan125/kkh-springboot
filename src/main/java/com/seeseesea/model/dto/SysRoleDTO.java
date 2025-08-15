package com.seeseesea.model.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;


/**
 * 角色表(SysRole)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-22 23:29:19
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysRoleDTO implements GrantedAuthority {

    /**
     * 角色ID
     **/
    private Long id;

    /**
     * 角色名称
     **/
    private String name;

    /**
     * 角色编码
     **/
    private String code;

    /**
     * 角色描述
     **/
    private String description;

    /**
     * 是否默认角色：0-否，1-是
     */
    private Integer isDefault;

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 更新时间
     **/
    private LocalDateTime updatedAt;

    private final static String prefix = "ROLE_";

    @Override
    public String getAuthority() {
        return code.startsWith(prefix) ? code : prefix + code;
    }
}

