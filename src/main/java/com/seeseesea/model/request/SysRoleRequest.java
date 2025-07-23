package com.seeseesea.model.request;


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
 * @since 2025-07-23 10:04:13
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysRoleRequest {

    /**
     * 角色ID
     **/
    private String id;
    /**
     * 角色名称
     **/
    private String name;
    /**
     * 角色名称
     **/
    private String code;
    /**
     * 角色描述
     **/
    private String description;
    /**
     * 是否默认角色：0-否，1-是
     **/
    private Integer isDefault;
}

