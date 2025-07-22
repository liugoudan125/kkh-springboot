package com.seeseesea.model.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 留言板表(MessageBoard)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-18 17:08:58
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MessageBoardRequest {

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

