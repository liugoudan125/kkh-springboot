package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seeseesea.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联表(SysUserRole)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-22 23:03:06
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

}

