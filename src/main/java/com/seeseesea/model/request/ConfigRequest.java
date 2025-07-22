package com.seeseesea.model.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 系统配置表(Config)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:57
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ConfigRequest {

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

