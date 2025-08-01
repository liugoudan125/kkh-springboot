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
 * 文章标签表(Tag)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:58
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Tag {

    /**
     * 主键ID
     **/
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 标签名称
     **/
    @TableField(value = "name")
    private String name;

    /**
     * 标签颜色
     **/
    @TableField(value = "color")
    private String color;

    /**
     * 标签描述
     **/
    @TableField(value = "description")
    private String description;

    /**
     * 使用该标签的文章数量
     **/
    @TableField(value = "article_count")
    private Integer articleCount;

    /**
     * 状态：active-启用，inactive-禁用
     **/
    @TableField(value = "status")
    private String status;

    /**
     * 创建时间
     **/
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     **/
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

}

