package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表(SysRole)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:38:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleRequest extends PageParams {

    /**
     * 角色ID
     **/
    private Long id;
    /**
     * 角色名称
     **/
    private String name;
    /**
     * 角色名称
     **/
    private String code;
    /**
     * 角色描述
     **/
    private String description;
    /**
     * 是否默认角色：0-否，1-是
     **/
    private Integer isDefault;
}

