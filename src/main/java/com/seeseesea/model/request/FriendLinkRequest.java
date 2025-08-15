package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 友链表(FriendLink)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:38:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FriendLinkRequest extends PageParams {

    /**
     * 主键ID
     **/
    private Long id;
    /**
     * 友链名称
     **/
    private String name;
    /**
     * 友链URL
     **/
    private String url;
    /**
     * 友链Logo
     **/
    private String logo;
    /**
     * 友链描述
     **/
    private String description;
    /**
     * 友链分类
     **/
    private String category;
    /**
     * 排序权重
     **/
    private Integer sortOrder;
    /**
     * 状态：active-启用，inactive-禁用
     **/
    private String status;
}

