package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 相册照片表(AlbumPhoto)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:39:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlbumPhotoRequest extends PageParams {

    /**
     * 主键ID
     **/
    private Long id;
    /**
     * 相册ID
     **/
    private Long albumId;
    /**
     * 照片标题
     **/
    private String title;
    /**
     * 照片描述
     **/
    private String description;
    /**
     * 图片URL
     **/
    private String imageUrl;
    /**
     * 排序权重
     **/
    private Integer sortOrder;
}

