package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.BlogTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签表(BlogTag)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:30
 */
@Mapper
public interface BlogTagDao extends BaseMapper<BlogTag> {

}

