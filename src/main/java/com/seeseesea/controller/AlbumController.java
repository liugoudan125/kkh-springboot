package com.seeseesea.controller;

import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.model.AlbumRequest;
import com.seeseesea.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AlbumController
 */
@RestController
@RequestMapping("album")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    /**
     * 创建相册
     */
    @PostMapping
    public BaseResponse<Void> add(@RequestBody @Validated(AlbumRequest.add.class) AlbumRequest request) {

    }

}
