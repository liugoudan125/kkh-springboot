package com.seeseesea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.seeseesea.model.dto.AlbumDTO;
import com.seeseesea.model.dto.AlbumPhotoDTO;
import com.seeseesea.model.dto.SysFileDTO;
import com.seeseesea.model.request.AlbumPhotoRequest;
import com.seeseesea.model.request.AlbumRequest;

import java.util.List;

/**
 * 相册表(Album)业务层接口
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
public interface AlbumService {

    AlbumDTO add(AlbumRequest request);

    IPage<AlbumDTO> page(AlbumRequest request);

    void update(AlbumRequest request);

    void addPhoto(Long albumId, List<SysFileDTO> sysFileDTOList);

    void deletePhoto(Long albumId, Long photoId);

    void updatePhoto(AlbumPhotoRequest request);

    IPage<AlbumPhotoDTO> pagePhoto(AlbumPhotoRequest request);

    void deleteAlbum(Long id);
}
