-- 博客系统数据库设计
-- 创建时间: 2024
-- 描述: 完整的博客系统数据库表结构

-- ==================== 用户相关表 ====================

-- 博客用户表
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`           bigint       NOT NULL COMMENT 'id',
    `nickname`     varchar(255) NOT NULL COMMENT '昵称（默认账户名）',
    `avatar`       varchar(255) DEFAULT NULL COMMENT '头像',
    `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
    `created_at`   datetime     NOT NULL COMMENT '创建时间',
    `updated_at`   datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci comment '用户表';

CREATE TABLE IF NOT EXISTS `sys_login_method`
(
    `id`           bigint       NOT NULL,
    `user_id`      bigint       NOT NULL COMMENT '用户ID',
    `method_type`  varchar(50)  NOT NULL COMMENT '登录方式(''email'',''username'',''github'',''wechat'',''gitee''）',
    `identifier`   varchar(255) NOT NULL COMMENT '邮箱、用户名、第三方平台用户ID',
    `access_token` varchar(255) DEFAULT NULL COMMENT '密码，access_token',
    `expires_at`   datetime     DEFAULT NULL COMMENT '如果第三方平台登录，令牌的过期时间',
    `created_at`   datetime     NOT NULL COMMENT '创建时间',
    `updated_at`   datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_identifer&methodType` (`method_type`, `identifier`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='登录方式表';

CREATE TABLE IF NOT EXISTS `sys_role`
(
    `id`          bigint      NOT NULL COMMENT '角色ID',
    `name`        varchar(50) NOT NULL COMMENT '角色名称',
    `code`        varchar(50) NOT NULL COMMENT '角色名称',
    `description` varchar(255)         DEFAULT NULL COMMENT '角色描述',
    `is_default`  int         NOT NULL DEFAULT 0 COMMENT '是否默认角色：0-否，1-是',
    `created_at`  datetime    NOT NULL COMMENT '创建时间',
    `updated_at`  datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';

CREATE TABLE IF NOT EXISTS `sys_user_role`
(
    `id`         bigint   NOT NULL COMMENT '主键ID',
    `user_id`    bigint   NOT NULL COMMENT '用户ID',
    `role_id`    bigint   NOT NULL COMMENT '角色ID',
    `created_at` datetime NOT NULL COMMENT '创建时间',
    `updated_at` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户角色关联表';


INSERT INTO `sys_user` (`id`, `nickname`, `avatar`, `introduction`, `created_at`, `updated_at`)
VALUES (1, '管理员', NULL, '系统管理员', NOW(), NOW());

INSERT INTO `sys_login_method` (`id`, `user_id`, `method_type`, `identifier`, `access_token`, `expires_at`,
                                `created_at`, `updated_at`)
VALUES (1, 1, 'username',
        'admin', '$2a$10$9Ul0Zi6cgyg6dyaX9zRuMOnvoraXakqTRbjNsOyWukAaQDVdlvSQK', NULL,
        NOW(), NOW());

INSERT INTO `sys_role` (`id`, `name`, `code`, `description`, `is_default`, `created_at`, `updated_at`)
values (1, '管理员', 'ADMIN', '系统管理员角色，拥有所有权限', 0, NOW(), NOW()),
       (2, '用户', 'USER', '普通访客角色，权限受限', 1, NOW(), NOW());

INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `created_at`, `updated_at`)
values (1, 1,
        1, NOW(), NOW());


-- ==================== 文章相关表 ====================

-- SysFile表创建脚本
CREATE TABLE IF NOT EXISTS `sys_file`
(
    `id`             bigint       NOT NULL COMMENT '主键ID',
    `bucket`         varchar(255) not null comment '在oss中的桶名称',
    `object_key`     varchar(255) not null comment '文件在oss中的key',
    `file_name`      varchar(255) NOT NULL COMMENT '文件原始名称',
    `file_digest`    varchar(64)  NOT NULL COMMENT '文件唯一标识（MD5或SHA256）',
    `file_size`      bigint       NOT NULL COMMENT '文件大小（字节）',
    `mime_type`      varchar(100) NOT NULL COMMENT '文件类型（如 image/jpeg,application/pdf）',
    `oss_url`        varchar(500) NOT NULL COMMENT 'OSS 文件存储地址',
    `upload_user_id` varchar(100) NOT NULL COMMENT '上传者用户ID',
    `upload_at`      datetime     NOT NULL COMMENT '文件上传时间',
    `created_at`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_upload_user_id` (`upload_user_id`),
    KEY `idx_upload_at` (`upload_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='系统文件表';

-- 文章分类表
CREATE TABLE IF NOT EXISTS `category`
(
    `id`          bigint      NOT NULL COMMENT '主键ID',
    `name`        varchar(50) NOT NULL COMMENT '分类名称',
    `description` varchar(200)         DEFAULT NULL COMMENT '分类描述',
    `icon`        varchar(100)         DEFAULT NULL COMMENT '分类图标',
    `sort_order`  int         NOT NULL DEFAULT 0 COMMENT '排序权重',
    `status`      varchar(20) NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
    `created_at`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name` (`name`),
    KEY `idx_sort_order` (`sort_order`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章分类表';

-- 文章专栏表
CREATE TABLE IF NOT EXISTS `series`
(
    `id`            bigint       NOT NULL COMMENT '主键ID',
    `name`          varchar(100) NOT NULL COMMENT '专栏名称',
    `description`   text                  DEFAULT NULL COMMENT '专栏描述',
    `cover_image`   varchar(500)          DEFAULT NULL COMMENT '封面图片URL',
    `author_id`     bigint       NOT NULL COMMENT '作者ID',
    `article_count` int          NOT NULL DEFAULT 0 COMMENT '文章数量',
    `status`        varchar(20)  NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
    `created_at`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_author_id` (`author_id`),
    KEY `idx_created_at` (`created_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章专栏表';

-- 文章标签表
CREATE TABLE IF NOT EXISTS `tag`
(
    `id`            bigint      NOT NULL COMMENT '主键ID',
    `name`          varchar(50) NOT NULL COMMENT '标签名称',
    `color`         varchar(20)          DEFAULT '#1890ff' COMMENT '标签颜色',
    `description`   varchar(200)         DEFAULT NULL COMMENT '标签描述',
    `article_count` int         NOT NULL DEFAULT 0 COMMENT '使用该标签的文章数量',
    `status`        varchar(20) NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
    `created_at`    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name` (`name`),
    KEY `idx_status` (`status`),
    KEY `idx_article_count` (`article_count`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章标签表';

-- 文章表
CREATE TABLE IF NOT EXISTS `article`
(
    `id`            bigint       NOT NULL COMMENT '主键ID',
    `title`         varchar(200) NOT NULL COMMENT '文章标题',
    `content`       longtext     NOT NULL COMMENT '文章内容（Markdown格式）',
    `summary`       text                  DEFAULT NULL COMMENT '文章摘要',
    `cover_image`   varchar(500)          DEFAULT NULL COMMENT '封面图片URL',
    `author_id`     bigint       NOT NULL COMMENT '作者ID',
    `category_id`   bigint                DEFAULT NULL COMMENT '分类ID',
    `series_id`     bigint                DEFAULT NULL COMMENT '专栏ID',
    `series_order`  int                   DEFAULT NULL COMMENT '在专栏中的排序',
    `view_count`    bigint       NOT NULL DEFAULT 0 COMMENT '浏览次数',
    `like_count`    bigint       NOT NULL DEFAULT 0 COMMENT '点赞次数',
    `comment_count` bigint       NOT NULL DEFAULT 0 COMMENT '评论次数',
    `status`        varchar(20)  NOT NULL DEFAULT 'draft' COMMENT '状态：draft-草稿，published-已发布，archived-已归档',
    `is_top`        tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否置顶：0-否，1-是',
    `allow_comment` tinyint(1)   NOT NULL DEFAULT 1 COMMENT '是否允许评论：0-否，1-是',
    `published_at`  datetime              DEFAULT NULL COMMENT '发布时间',
    `created_at`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_author_id` (`author_id`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_series_id` (`series_id`),
    KEY `idx_status` (`status`),
    KEY `idx_created_at` (`created_at`),
    FULLTEXT KEY `ft_title_content` (`title`, `content`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章表';

-- 文章标签关联表
CREATE TABLE IF NOT EXISTS `article_tag`
(
    `id`         bigint   NOT NULL COMMENT '主键ID',
    `article_id` bigint   NOT NULL COMMENT '文章ID',
    `tag_id`     bigint   NOT NULL COMMENT '标签ID',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_article_tag` (`article_id`, `tag_id`),
    KEY `idx_article_id` (`article_id`),
    KEY `idx_tag_id` (`tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章标签关联表';

-- ==================== 相册相关表 ====================

-- 相册表
CREATE TABLE IF NOT EXISTS `album`
(
    `id`          bigint       NOT NULL COMMENT '主键ID',
    `name`        varchar(100) NOT NULL COMMENT '相册名称',
    `description` text                  DEFAULT NULL COMMENT '相册描述',
    `cover_image` varchar(500)          DEFAULT NULL COMMENT '封面图片URL',
    `author_id`   bigint       NOT NULL COMMENT '创建者ID',
    `status`      varchar(20)  NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
    `created_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_created_at` (`created_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='相册表';

-- 相册照片表
CREATE TABLE IF NOT EXISTS `album_photo`
(
    `id`          bigint       NOT NULL COMMENT '主键ID',
    `album_id`    bigint       NOT NULL COMMENT '相册ID',
    `title`       varchar(200)          DEFAULT NULL COMMENT '照片标题',
    `description` text                  DEFAULT NULL COMMENT '照片描述',
    `image_url`   varchar(500) NOT NULL COMMENT '图片URL',
    `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '排序权重',
    `created_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_album_url` (`album_id`, `image_url`),
    KEY `idx_album_id` (`album_id`),
    KEY `idx_sort_order` (`sort_order`),
    KEY `idx_created_at` (`created_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='相册照片表';

-- ==================== 留言板相关表 ====================

-- 留言板表
CREATE TABLE IF NOT EXISTS `message_board`
(
    `id`          bigint       NOT NULL COMMENT '主键ID',
    `name`        varchar(100) NOT NULL COMMENT '留言板名称',
    `description` text                  DEFAULT NULL COMMENT '留言板描述',
    `status`      varchar(20)  NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
    `created_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='留言板表';

-- 留言表
CREATE TABLE IF NOT EXISTS `message`
(
    `id`         bigint      NOT NULL COMMENT '主键ID',
    `board_id`   bigint      NOT NULL COMMENT '留言板ID',
    `user_id`    bigint               DEFAULT NULL COMMENT '用户ID（可为空，支持匿名留言）',
    `nickname`   varchar(50) NOT NULL COMMENT '留言者昵称',
    `email`      varchar(100)         DEFAULT NULL COMMENT '留言者邮箱',
    `website`    varchar(200)         DEFAULT NULL COMMENT '留言者网站',
    `avatar`     varchar(500)         DEFAULT NULL COMMENT '留言者头像',
    `content`    text        NOT NULL COMMENT '留言内容',
    `ip_address` varchar(50)          DEFAULT NULL COMMENT 'IP地址',
    `user_agent` varchar(500)         DEFAULT NULL COMMENT '用户代理',
    `status`     varchar(20) NOT NULL DEFAULT 'pending' COMMENT '状态：pending-待审核，approved-已通过，rejected-已拒绝',
    `created_at` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_board_id` (`board_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`),
    KEY `idx_created_at` (`created_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='留言表';

-- ==================== 评论相关表 ====================

-- 评论表
CREATE TABLE IF NOT EXISTS `comment`
(
    `id`         bigint      NOT NULL COMMENT '主键ID',
    `article_id` bigint      NOT NULL COMMENT '文章ID',
    `user_id`    bigint      NOT NULL COMMENT '用户ID（可为空，支持匿名评论）',
    `nickname`   varchar(50) NOT NULL COMMENT '评论者昵称',
    `parent_id`  bigint               DEFAULT NULL COMMENT '父评论ID（用于回复功能）',
    `content`    text        NOT NULL COMMENT '评论内容',
    `ip_address` varchar(50)          DEFAULT NULL COMMENT 'IP地址',
    `user_agent` varchar(500)         DEFAULT NULL COMMENT '用户代理',
    `like_count` int         NOT NULL DEFAULT 0 COMMENT '点赞次数',
    `status`     varchar(20) NOT NULL DEFAULT 'pending' COMMENT '状态：pending-待审核，approved-已通过，rejected-已拒绝',
    `created_at` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_article_id` (`article_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_status` (`status`),
    KEY `idx_created_at` (`created_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='评论表';

-- ==================== 友链相关表 ====================

-- 友链表
CREATE TABLE IF NOT EXISTS `friend_link`
(
    `id`          bigint       NOT NULL COMMENT '主键ID',
    `name`        varchar(100) NOT NULL COMMENT '友链名称',
    `url`         varchar(500) NOT NULL COMMENT '友链URL',
    `logo`        varchar(500) NOT NULL COMMENT '友链Logo',
    `description` varchar(200)          DEFAULT NULL COMMENT '友链描述',
    `category`    varchar(50)           DEFAULT 'default' COMMENT '友链分类',
    `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '排序权重',
    `status`      varchar(20)  NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
    `created_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='友链表';

-- ==================== 统计相关表 ====================

-- 文章浏览记录表
CREATE TABLE IF NOT EXISTS `article_view_log`
(
    `id`         bigint      NOT NULL COMMENT '主键ID',
    `article_id` bigint      NOT NULL COMMENT '文章ID',
    `ip_address` varchar(50) NOT NULL COMMENT 'IP地址',
    `user_agent` varchar(500)         DEFAULT NULL COMMENT '用户代理',
    `referer`    varchar(500)         DEFAULT NULL COMMENT '来源页面',
    `viewed_at`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
    PRIMARY KEY (`id`),
    KEY `idx_article_id` (`article_id`),
    KEY `idx_ip_address` (`ip_address`),
    KEY `idx_viewed_at` (`viewed_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章浏览记录表';

-- ==================== 初始化数据 ====================

-- 插入默认留言板
INSERT INTO `message_board` (`id`, `name`, `description`)
VALUES ('1', '默认留言板', '欢迎在这里留言交流！');

