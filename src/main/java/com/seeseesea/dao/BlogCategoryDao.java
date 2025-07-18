package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.BlogCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章分类表(BlogCategory)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:28
 */
@Mapper
public interface BlogCategoryDao extends BaseMapper<BlogCategory> {

}

