package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.BlogConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置表(BlogConfig)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:29
 */
@Mapper
public interface BlogConfigDao extends BaseMapper<BlogConfig> {

}

