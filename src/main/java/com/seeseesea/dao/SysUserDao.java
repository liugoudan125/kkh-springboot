package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seeseesea.model.SysRole;
import com.seeseesea.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-03 11:20:12
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

    List<SysRole> listRoleByUserId(Long userId);

}

