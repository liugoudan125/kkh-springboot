package com.seeseesea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.seeseesea.model.AlbumDTO;
import com.seeseesea.model.AlbumPhotoDTO;
import com.seeseesea.model.AlbumPhotoRequest;
import com.seeseesea.model.request.AlbumRequest;
import com.seeseesea.model.SysFileDTO;

import java.util.List;

/**
 * 相册表(Album)业务层接口
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:55
 */
public interface AlbumService {

    void add(AlbumRequest request);

    IPage<AlbumDTO> page(AlbumRequest request);

    void update(AlbumRequest request);

    void addPhoto(String albumId, List<SysFileDTO> sysFileDTOList);

    void deletePhoto(String albumId, String photoId);

    void updatePhoto(AlbumPhotoRequest request);

    IPage<AlbumPhotoDTO> pagePhoto(AlbumPhotoRequest request);
}
