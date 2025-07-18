package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.BlogSeries;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章专栏表(BlogSeries)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:30
 */
@Mapper
public interface BlogSeriesDao extends BaseMapper<BlogSeries> {

}

