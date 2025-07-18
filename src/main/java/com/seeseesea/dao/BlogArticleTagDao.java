package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.BlogArticleTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签关联表(BlogArticleTag)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:28
 */
@Mapper
public interface BlogArticleTagDao extends BaseMapper<BlogArticleTag> {

}

