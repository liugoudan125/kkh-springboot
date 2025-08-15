package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seeseesea.model.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签表(Tag)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:58
 */
@Mapper
public interface TagDao extends BaseMapper<Tag> {

}

