package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置表(Config)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:56
 */
@Mapper
public interface ConfigDao extends BaseMapper<Config> {

}

