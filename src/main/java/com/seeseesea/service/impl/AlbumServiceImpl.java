package com.seeseesea.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.seeseesea.core.utils.BeanCopyUtils;
import com.seeseesea.core.utils.UserUtils;
import com.seeseesea.dao.AlbumDao;
import com.seeseesea.dao.AlbumPhotoDao;
import com.seeseesea.model.Album;
import com.seeseesea.model.AlbumPhoto;
import com.seeseesea.model.dto.AlbumDTO;
import com.seeseesea.model.dto.AlbumPhotoDTO;
import com.seeseesea.model.dto.SysFileDTO;
import com.seeseesea.model.request.AlbumPhotoRequest;
import com.seeseesea.model.request.AlbumRequest;
import com.seeseesea.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 相册表(Album)业务层接口实现类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
@RequiredArgsConstructor
@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumDao albumDao;
    private final AlbumPhotoDao albumPhotoDao;

    @Override
    public AlbumDTO add(AlbumRequest request) {

        Album album = BeanCopyUtils.copy(request, Album::new);
        album.setAuthorId(UserUtils.getUserId());
        album.setStatus(Album.Status.ACTIVE);
        albumDao.insert(album);
        album = albumDao.selectById(album.getId());
        return BeanCopyUtils.copy(album, AlbumDTO::new);
    }

    @Override
    public IPage<AlbumDTO> page(AlbumRequest request) {
        IPage<Album> page = albumDao.page(request);
        return page.convert(album -> BeanCopyUtils.copy(album, AlbumDTO::new));
    }

    @Override
    public void update(AlbumRequest request) {
        Album album = BeanCopyUtils.copy(request, Album::new);
        albumDao.updateById(album);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPhoto(Long albumId, List<SysFileDTO> sysFileDTOList) {
        if (ObjectUtils.isNotEmpty(sysFileDTOList)) {
            List<AlbumPhoto> albumPhotoList = new ArrayList<>();
            for (SysFileDTO sysFileDTO : sysFileDTOList) {
                if (albumPhotoDao.existsByAlbumIdAndImageUrl(albumId, sysFileDTO.getOssUrl())) {
                    continue; // 如果相册中已存在该图片，则跳过
                }
                AlbumPhoto photo = new AlbumPhoto();
                photo.setAlbumId(albumId);
                photo.setImageUrl(sysFileDTO.getOssUrl());
                photo.setTitle(sysFileDTO.getFileName());
                albumPhotoList.add(photo);
            }
            albumPhotoDao.insert(albumPhotoList);
        }
    }

    @Override
    public void deletePhoto(Long albumId, Long photoId) {
        albumPhotoDao.deleteByAlbumIdAndPhotoId(albumId, photoId);
    }

    @Override
    public void updatePhoto(AlbumPhotoRequest request) {
        AlbumPhoto albumPhoto = BeanCopyUtils.copy(request, AlbumPhoto::new);
        albumPhotoDao.updateByAlbumIdAndId(albumPhoto);
    }

    @Override
    public IPage<AlbumPhotoDTO> pagePhoto(AlbumPhotoRequest request) {
        IPage<AlbumPhoto> page = albumPhotoDao.page(request);
        return page.convert(album -> BeanCopyUtils.copy(album, AlbumPhotoDTO::new));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAlbum(Long id) {
        //删除相册下所有相片
        albumPhotoDao.deleteByAlbumId(id);
        //删除相册
        albumDao.deleteById(id);
    }
}
