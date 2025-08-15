package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seeseesea.model.AlbumPhoto;
import com.seeseesea.model.request.AlbumPhotoRequest;
import org.apache.ibatis.annotations.Mapper;

/**
 * 相册照片表(AlbumPhoto)表数据库访问层
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
@Mapper
public interface AlbumPhotoDao extends BaseMapper<AlbumPhoto> {

    default void deleteByAlbumIdAndPhotoId(Long albumId, Long photoId) {
        new LambdaUpdateChainWrapper<>(this)
                .eq(AlbumPhoto::getId, photoId)
                .eq(AlbumPhoto::getAlbumId, albumId)
                .remove();
    }

    default void updateByAlbumIdAndId(AlbumPhoto albumPhoto) {
        new LambdaUpdateChainWrapper<>(this)
                .eq(AlbumPhoto::getId, albumPhoto.getId())
                .eq(AlbumPhoto::getAlbumId, albumPhoto.getAlbumId())
                .update(albumPhoto);
    }

    default IPage<AlbumPhoto> page(AlbumPhotoRequest request) {
        return new LambdaQueryChainWrapper<>(this)
                .eq(AlbumPhoto::getAlbumId, request.getAlbumId())
                .orderByAsc(AlbumPhoto::getSortOrder)
                .orderByDesc(AlbumPhoto::getCreatedAt)
                .page(Page.of(request.getCurrent(), request.getPageSize()));
    }

    default boolean existsByAlbumIdAndImageUrl(Long albumId, String ossUrl) {
        return new LambdaQueryChainWrapper<>(this)
                .eq(AlbumPhoto::getAlbumId, albumId)
                .eq(AlbumPhoto::getImageUrl, ossUrl)
                .exists();
    }

    default void deleteByAlbumId(Long albumId) {
        new LambdaUpdateChainWrapper<>(this)
                .eq(AlbumPhoto::getAlbumId, albumId)
                .remove();
    }
}

