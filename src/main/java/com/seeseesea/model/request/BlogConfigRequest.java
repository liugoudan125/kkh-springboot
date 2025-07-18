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
 * 系统配置表(BlogConfig)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-17 22:58:30
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogConfigRequest {

    /**
     * 主键ID
     **/
    private String id;
    /**
     * 配置键
     **/
    private String configKey;
    /**
     * 配置值
     **/
    private String configValue;
    /**
     * 配置描述
     **/
    private String description;
}

