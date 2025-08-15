package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seeseesea.model.ArticleViewLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章浏览记录表(ArticleViewLog)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:56
 */
@Mapper
public interface ArticleViewLogDao extends BaseMapper<ArticleViewLog> {

}

