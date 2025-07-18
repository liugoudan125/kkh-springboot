package com.seeseesea.model;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


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
public class FriendLinkDTO {

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

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     **/
    private LocalDateTime updatedAt;

}

