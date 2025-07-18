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
 * 留言板表(BlogMessageBoard)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-17 22:58:30
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogMessageBoardRequest {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 留言板名称
     **/
    private String name;
    /**
     * 留言板描述
     **/
    private String description;
    /**
     * 状态：active-启用，inactive-禁用
     **/
    private String status;
}

