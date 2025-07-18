package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.BlogAlbum;
import org.apache.ibatis.annotations.Mapper;

/**
 * 相册表(BlogAlbum)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:27
 */
@Mapper
public interface BlogAlbumDao extends BaseMapper<BlogAlbum> {

}

