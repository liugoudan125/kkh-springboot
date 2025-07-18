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
 * 系统配置表(BlogConfig)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:29
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogConfigDTO {

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

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     **/
    private LocalDateTime updatedAt;

}

