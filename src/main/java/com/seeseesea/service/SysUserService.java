package com.seeseesea.service;

import com.seeseesea.model.SysRoleDTO;
import com.seeseesea.model.SysUser;
import com.seeseesea.model.SysUserDTO;

import java.util.List;

/**
 * (SysUser)业务层接口
 *
 * @author liuchenglong
 * @since 2025-07-02 22:25:41
 */
public interface SysUserService {

    void save(SysUser sysUser);

    SysUserDTO getById(String userId);

    List<SysRoleDTO> listRoleByUserId(String userId);

    void linkRoles(String userId, List<String> roleIds);
}
