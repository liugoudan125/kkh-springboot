package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seeseesea.core.constants.Is;
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

    default SysRole getDefaultRole() {
        return new LambdaQueryChainWrapper<>(this)
                .eq(SysRole::getIsDefault, Is.YES)
                .one();
    }
}

