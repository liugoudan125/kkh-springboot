package com.seeseesea.service.impl;


import com.seeseesea.core.utils.BeanCopyUtils;
import com.seeseesea.dao.SysUserDao;
import com.seeseesea.dao.SysUserRoleDao;
import com.seeseesea.model.SysRole;
import com.seeseesea.model.SysUser;
import com.seeseesea.model.SysUserRole;
import com.seeseesea.model.dto.SysRoleDTO;
import com.seeseesea.model.dto.SysUserDTO;
import com.seeseesea.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysUser)业务层接口实现类
 *
 * @author liuchenglong
 * @since 2025-07-02 22:25:41
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserDao sysUserDao;
    private final SysUserRoleDao sysUserRoleDao;

    @Override
    public void save(SysUser sysUser) {
        sysUserDao.insert(sysUser);
    }

    @Override
    public SysUserDTO getById(Long userId) {
        SysUser sysUser = sysUserDao.selectById(userId);
        if (sysUser == null) {
            return null;
        }
        return BeanCopyUtils.copy(sysUser, SysUserDTO::new);
    }

    @Override
    public List<SysRoleDTO> listRoleByUserId(Long userId) {
        List<SysRole> sysRoleList = sysUserDao.listRoleByUserId(userId);
        return BeanCopyUtils.copyList(sysRoleList, SysRoleDTO.class);
    }

    @Override
    public void linkRoles(Long userId, List<Long> roleIds) {
        List<SysUserRole> sysUserRoleList = roleIds.stream()
                .distinct()
                .map(roleId -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userId);
                    sysUserRole.setRoleId(roleId);
                    return sysUserRole;
                }).toList();
        sysUserRoleDao.insert(sysUserRoleList);
    }
}
