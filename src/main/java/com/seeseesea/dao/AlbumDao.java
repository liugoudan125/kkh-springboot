package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seeseesea.model.request.AlbumRequest;
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

    default IPage<Album> page(AlbumRequest request) {

        return new LambdaQueryChainWrapper<>(this)
                .like(request.getName() != null, Album::getName, request.getName())
                .eq(request.getStatus() != null, Album::getStatus, request.getStatus())
                .orderByDesc(Album::getCreatedAt)
                .page(Page.of(request.getCurrent(), request.getSize()));
    }

}

