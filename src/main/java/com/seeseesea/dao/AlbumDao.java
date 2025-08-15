package com.seeseesea.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seeseesea.model.Album;
import com.seeseesea.model.request.AlbumRequest;
import org.apache.commons.lang3.StringUtils;
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
                .like(StringUtils.isNotBlank(request.getName()), Album::getName, request.getName())
                .eq(StringUtils.isNotBlank(request.getStatus()), Album::getStatus, request.getStatus())
                .orderByDesc(Album::getCreatedAt)
                .page(Page.of(request.getCurrent(), request.getPageSize()));
    }

}

