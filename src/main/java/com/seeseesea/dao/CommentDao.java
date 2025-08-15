package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seeseesea.model.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:56
 */
@Mapper
public interface CommentDao extends BaseMapper<Comment> {

}

