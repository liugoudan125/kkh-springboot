package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
@Mapper
public interface ArticleDao extends BaseMapper<Article> {

}

