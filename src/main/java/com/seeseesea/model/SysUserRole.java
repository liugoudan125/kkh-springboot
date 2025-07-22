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
 * 用户角色关联表(SysUserRole)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-22 23:03:06
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysUserRole {

    /**
     * 主键ID
     **/
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户ID
     **/
    @TableField(value = "user_id")
    private String userId;

    /**
     * 角色ID
     **/
    @TableField(value = "role_id")
    private String roleId;

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

