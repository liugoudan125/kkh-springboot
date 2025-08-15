package com.seeseesea.model.request;


import com.seeseesea.core.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 留言板表(MessageBoard)表实体类
 *
 * @author liuchenglong
 * @since 2025-08-14 11:38:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageBoardRequest extends PageParams {

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
}

