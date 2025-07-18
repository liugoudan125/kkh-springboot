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
 * (SysUser)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-17 22:58:30
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysUserRequest {

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
}

