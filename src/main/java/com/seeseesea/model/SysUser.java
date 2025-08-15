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
 * 用户表(SysUser)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-12 22:07:03
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysUser {

    /**
     * id
     **/
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 昵称（默认账户名）
     **/
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 头像
     **/
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 简介
     **/
    @TableField(value = "introduction")
    private String introduction;

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

