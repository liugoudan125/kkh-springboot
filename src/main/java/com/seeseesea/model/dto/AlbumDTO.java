package com.seeseesea.model.dto;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * 相册表(Album)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:29:19
 */
@Data
public class AlbumDTO {

    /**
     * 主键ID
     **/
    private Long id;

    /**
     * 相册名称
     **/
    private String name;

    /**
     * 相册描述
     **/
    private String description;

    /**
     * 封面图片URL
     **/
    private String coverImage;

    /**
     * 创建者ID
     **/
    private Long authorId;

    /**
     * 状态：active-启用，inactive-禁用
     **/
    private String status;

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     **/
    private LocalDateTime updatedAt;

}

