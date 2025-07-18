package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.BlogFriendLink;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友链表(BlogFriendLink)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:29
 */
@Mapper
public interface BlogFriendLinkDao extends BaseMapper<BlogFriendLink> {

}

