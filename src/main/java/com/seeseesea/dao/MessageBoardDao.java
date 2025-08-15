package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seeseesea.model.MessageBoard;
import org.apache.ibatis.annotations.Mapper;

/**
 * 留言板表(MessageBoard)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:57
 */
@Mapper
public interface MessageBoardDao extends BaseMapper<MessageBoard> {

}

