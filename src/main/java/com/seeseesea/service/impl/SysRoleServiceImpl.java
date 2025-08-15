package com.seeseesea.service.impl;


import com.seeseesea.core.utils.BeanCopyUtils;
import com.seeseesea.dao.SysRoleDao;
import com.seeseesea.model.SysRole;
import com.seeseesea.model.dto.SysRoleDTO;
import com.seeseesea.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色表(SysRole)业务层接口实现类
 *
 * @author liuchenglong
 * @since 2025-07-22 23:03:19
 */
@RequiredArgsConstructor
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleDao sysRoleDao;

    @Override
    public SysRoleDTO getDefaultRole() {
        SysRole sysRole = sysRoleDao.getDefaultRole();
        return BeanCopyUtils.copy(sysRole, SysRoleDTO::new);
    }
}
