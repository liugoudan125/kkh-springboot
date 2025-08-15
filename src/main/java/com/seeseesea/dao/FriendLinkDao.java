package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seeseesea.model.FriendLink;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友链表(FriendLink)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:57
 */
@Mapper
public interface FriendLinkDao extends BaseMapper<FriendLink> {

}

