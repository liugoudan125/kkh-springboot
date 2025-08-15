package com.seeseesea.model;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 用户角色关联表(SysUserRole)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:33:32
 */
@Data
public class SysUserRole {

    /**
     * 主键ID
     **/
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户ID
     **/
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色ID
     **/
    @TableField(value = "role_id")
    private Long roleId;

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

