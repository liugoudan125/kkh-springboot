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
 * @since 2025-07-18 17:08:59
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class TagDTO {

    /**
     * 主键ID
     **/
    private String id;

    /**
     * 标签名称
     **/
    private String name;

    /**
     * 标签颜色
     **/
    private String color;

    /**
     * 标签描述
     **/
    private String description;

    /**
     * 使用该标签的文章数量
     **/
    private Integer articleCount;

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

