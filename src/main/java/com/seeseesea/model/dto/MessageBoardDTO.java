package com.seeseesea.model.dto;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * 留言板表(MessageBoard)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:33:31
 */
@Data
public class MessageBoardDTO {

    /**
     * 主键ID
     **/
    private Long id;

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

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

    /**
     * 最后更新时间
     **/
    private LocalDateTime updatedAt;

}

