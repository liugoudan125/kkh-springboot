package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.seeseesea.model.BlogAlbumPhoto;
import org.apache.ibatis.annotations.Mapper;

/**
 * 相册照片表(BlogAlbumPhoto)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:28
 */
@Mapper
public interface BlogAlbumPhotoDao extends BaseMapper<BlogAlbumPhoto> {

}

