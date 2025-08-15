package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seeseesea.model.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
@Mapper
public interface ArticleTagDao extends BaseMapper<ArticleTag> {

}

