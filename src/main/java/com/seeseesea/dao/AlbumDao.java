package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.Album;
import org.apache.ibatis.annotations.Mapper;

/**
 * 相册表(Album)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:54
 */
@Mapper
public interface AlbumDao extends BaseMapper<Album> {

}

