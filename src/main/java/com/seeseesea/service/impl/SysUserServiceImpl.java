package com.seeseesea.service.impl;


import com.seeseesea.core.utils.BeanCopyUtils;
import com.seeseesea.dao.SysUserDao;
import com.seeseesea.model.SysUser;
import com.seeseesea.model.SysUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.seeseesea.service.SysUserService;

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

    @Override
    public void save(SysUser sysUser) {
        sysUserDao.insert(sysUser);
    }

    @Override
    public SysUserDTO getById(String userId) {
        SysUser sysUser = sysUserDao.selectById(userId);
        if (sysUser == null) {
            return null;
        }
        return BeanCopyUtils.copy(sysUser, SysUserDTO::new);
    }
}
