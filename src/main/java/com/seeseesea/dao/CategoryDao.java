package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章分类表(Category)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:56
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

}

