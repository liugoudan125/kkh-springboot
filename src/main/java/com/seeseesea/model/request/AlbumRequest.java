package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 相册表(Album)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:54
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class AlbumRequest extends PageParams {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 相册名称
     **/
    @NotBlank(message = "相册名称不能为空", groups = {add.class})
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
    private String authorId;
    /**
     * 状态：active-启用，inactive-禁用
     **/
    private String status;

    public interface add {
    }

}

