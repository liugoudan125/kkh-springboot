package com.seeseesea.service;

import com.seeseesea.model.dto.SysRoleDTO;

/**
 * 角色表(SysRole)业务层接口
 *
 * @author liuchenglong
 * @since 2025-07-22 23:03:19
 */
public interface SysRoleService {

    SysRoleDTO getDefaultRole();
}
