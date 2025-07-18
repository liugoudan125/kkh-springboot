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
 * 文章标签关联表(BlogArticleTag)表实体类
 *
 * @author liuchenglong
 * @since 2025-07-11 14:47:28
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlogArticleTagDTO {

    /**
     * 主键ID
     **/
    private String id;

    /**
     * 文章ID
     **/
    private String articleId;

    /**
     * 标签ID
     **/
    private String tagId;

    /**
     * 创建时间
     **/
    private LocalDateTime createdAt;

}

