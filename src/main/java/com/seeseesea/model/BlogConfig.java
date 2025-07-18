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
 * 系统配置表(BlogConfig)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:29
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogConfig {

    /**
     * 主键ID
     **/
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 配置键
     **/
    @TableField(value = "config_key")
    private String configKey;

    /**
     * 配置值
     **/
    @TableField(value = "config_value")
    private String configValue;

    /**
     * 配置描述
     **/
    @TableField(value = "description")
    private String description;

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

