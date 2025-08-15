package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 相册表(Album)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-12 22:07:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlbumRequest extends PageParams {

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

    public interface add {
    }
}

