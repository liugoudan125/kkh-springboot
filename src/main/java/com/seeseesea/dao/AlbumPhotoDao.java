package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.AlbumPhoto;
import org.apache.ibatis.annotations.Mapper;

/**
 * 相册照片表(AlbumPhoto)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
@Mapper
public interface AlbumPhotoDao extends BaseMapper<AlbumPhoto> {

}

