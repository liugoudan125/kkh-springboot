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
 * 权限表(SysPermission)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-22 23:03:19
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysPermissionRequest {

    /**
     * 权限ID
     **/
    private String id;
    /**
     * 权限名称
     **/
    private String name;
    /**
     * 权限代码（唯一标识）user:create
     **/
    private String code;
    /**
     * 权限描述
     **/
    private String description;
}

