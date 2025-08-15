package com.seeseesea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.model.dto.AlbumDTO;
import com.seeseesea.model.dto.AlbumPhotoDTO;
import com.seeseesea.model.dto.SysFileDTO;
import com.seeseesea.model.request.AlbumPhotoRequest;
import com.seeseesea.model.request.AlbumRequest;
import com.seeseesea.service.AlbumService;
import com.seeseesea.service.SysFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * AlbumController
 */
@RestController
@RequestMapping("album")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    private final SysFileService sysFileService;

    /**
     * 创建相册
     */
    @PostMapping
    public BaseResponse<AlbumDTO> create(@RequestBody @Validated(AlbumRequest.add.class) AlbumRequest request) {
        AlbumDTO albumDTO = albumService.add(request);
        return BaseResponse.ok(albumDTO);
    }

    /**
     * 获取相册列表（分页查询）
     */
    @GetMapping()
    public BaseResponse<IPage<AlbumDTO>> page(AlbumRequest request) {
        return BaseResponse.ok(albumService.page(request));
    }

    /**
     * 更新相册
     */
    @PutMapping("{id}")
    public BaseResponse<Void> update(@RequestBody AlbumRequest request, @PathVariable Long id) {
        request.setId(id);
        albumService.update(request);
        return BaseResponse.ok();
    }

    /**
     * 删除相册
     */
    @DeleteMapping("{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return BaseResponse.ok();
    }

    /**
     * 查询相册下的相片列表（分页）
     */
    @GetMapping("{albumId}/photo")
    public BaseResponse<IPage<AlbumPhotoDTO>> getPhotos(@PathVariable Long albumId, AlbumPhotoRequest request) {
        request.setAlbumId(albumId);
        IPage<AlbumPhotoDTO> page = albumService.pagePhoto(request);
        return BaseResponse.ok(page);
    }

    /**
     * 添加相片(上传相片)
     */
    @PostMapping("{albumId}/photo")
    public BaseResponse<Void> addPhoto(@PathVariable Long albumId, List<MultipartFile> files) {
        List<SysFileDTO> sysFileDTOList = sysFileService.uploadFiles(files);
        albumService.addPhoto(albumId, sysFileDTOList);
        return BaseResponse.ok();
    }

    /**
     * 删除相片
     */
    @DeleteMapping("{albumId}/photo/{photoId}")
    public BaseResponse<Void> deletePhoto(@PathVariable Long albumId, @PathVariable Long photoId) {
        albumService.deletePhoto(albumId, photoId);
        return BaseResponse.ok();
    }

    /**
     * 修改相片
     */
    @PutMapping("{albumId}/photo/{photoId}")
    public BaseResponse<Void> updatePhoto(@PathVariable Long albumId, @PathVariable Long photoId, @RequestBody AlbumPhotoRequest request) {
        request.setAlbumId(albumId);
        request.setId(photoId);
        albumService.updatePhoto(request);
        return BaseResponse.ok();
    }
}
