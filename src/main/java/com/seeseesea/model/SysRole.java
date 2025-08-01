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
 * 角色表(SysRole)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-23 09:49:57
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysRole {

    /**
     * 角色ID
     **/
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 角色名称
     **/
    @TableField(value = "name")
    private String name;

    /**
     * 角色名称
     **/
    @TableField(value = "code")
    private String code;

    /**
     * 角色描述
     **/
    @TableField(value = "description")
    private String description;

    /**
     * 是否默认角色：0-否，1-是
     **/
    @TableField(value = "is_default")
    private Integer isDefault;

    /**
     * 创建时间
     **/
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     **/
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

}

