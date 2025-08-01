package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.Series;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章专栏表(Series)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:58
 */
@Mapper
public interface SeriesDao extends BaseMapper<Series> {

}

