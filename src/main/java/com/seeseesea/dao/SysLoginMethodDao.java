package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seeseesea.model.SysUser;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.SysLoginMethod;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SysLoginMethod)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-03 11:20:12
 */
@Mapper
public interface SysLoginMethodDao extends BaseMapper<SysLoginMethod> {

    default SysLoginMethod selectByIdentifierAndMethodType(String identifier, String methodType) {
        return selectOne(new LambdaQueryWrapper<SysLoginMethod>()
                .eq(SysLoginMethod::getIdentifier, identifier)
                .eq(SysLoginMethod::getMethodType, methodType)
        );
    }
}

