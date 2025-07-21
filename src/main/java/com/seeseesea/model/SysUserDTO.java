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
 * (SysUser)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-03 11:21:16
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysUserDTO {

    /**
     * id
     **/
    private String id;

    /**
     * 昵称（默认账户名）
     **/
    private String nickname;

    /**
     * 头像
     **/
    private String avatar;

    /**
     * 简介
     **/
    private String introduction;

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 更新时间
     **/
    private LocalDateTime updatedAt;

}

