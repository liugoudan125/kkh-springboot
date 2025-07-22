package com.seeseesea.model.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 友链表(FriendLink)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:57
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class FriendLinkRequest {

    /**
     * 主键ID
     **/
    private String id;
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

