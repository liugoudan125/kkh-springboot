package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-22 23:03:19
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {

}

