package com.seeseesea.model;


import com.seeseesea.core.page.PageParams;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 相册照片表(AlbumPhoto)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-22 16:11:20
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class AlbumPhotoRequest extends PageParams {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 相册ID
     **/
    private String albumId;
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

