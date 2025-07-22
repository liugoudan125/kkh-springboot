-- 博客系统数据库设计
-- 创建时间: 2024
-- 描述: 完整的博客系统数据库表结构

-- ==================== 用户相关表 ====================

-- 博客用户表（扩展用户信息）
CREATE TABLE IF NOT EXISTS `blog_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` varchar(100) NOT NULL COMMENT '关联系统用户ID',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像URL',
  `bio` text DEFAULT NULL COMMENT '个人简介',
  `website` varchar(200) DEFAULT NULL COMMENT '个人网站',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '状态：active-活跃，inactive-非活跃，banned-禁用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='博客用户表';

-- ==================== 文章相关表 ====================

-- 文章分类表
CREATE TABLE IF NOT EXISTS `blog_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `description` varchar(200) DEFAULT NULL COMMENT '分类描述',
  `icon` varchar(100) DEFAULT NULL COMMENT '分类图标',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序权重',
  `status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  KEY `idx_status` (`status`),
  KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章分类表';

-- 文章专栏表
CREATE TABLE IF NOT EXISTS `blog_series` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '专栏名称',
  `description` text DEFAULT NULL COMMENT '专栏描述',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '封面图片URL',
  `author_id` bigint NOT NULL COMMENT '作者ID',
  `article_count` int NOT NULL DEFAULT 0 COMMENT '文章数量',
  `status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_author_id` (`author_id`),
  KEY `idx_status` (`status`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章专栏表';

-- 文章标签表
CREATE TABLE IF NOT EXISTS `blog_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '标签名称',
  `color` varchar(20) DEFAULT '#1890ff' COMMENT '标签颜色',
  `description` varchar(200) DEFAULT NULL COMMENT '标签描述',
  `article_count` int NOT NULL DEFAULT 0 COMMENT '使用该标签的文章数量',
  `status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  KEY `idx_status` (`status`),
  KEY `idx_article_count` (`article_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章标签表';

-- 文章表
CREATE TABLE IF NOT EXISTS `blog_article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) NOT NULL COMMENT '文章标题',
  `content` longtext NOT NULL COMMENT '文章内容（Markdown格式）',
  `summary` text DEFAULT NULL COMMENT '文章摘要',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '封面图片URL',
  `author_id` bigint NOT NULL COMMENT '作者ID',
  `category_id` bigint DEFAULT NULL COMMENT '分类ID',
  `series_id` bigint DEFAULT NULL COMMENT '专栏ID',
  `series_order` int DEFAULT NULL COMMENT '在专栏中的排序',
  `view_count` bigint NOT NULL DEFAULT 0 COMMENT '浏览次数',
  `like_count` bigint NOT NULL DEFAULT 0 COMMENT '点赞次数',
  `comment_count` bigint NOT NULL DEFAULT 0 COMMENT '评论次数',
  `status` varchar(20) NOT NULL DEFAULT 'draft' COMMENT '状态：draft-草稿，published-已发布，archived-已归档',
  `is_top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否置顶：0-否，1-是',
  `allow_comment` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否允许评论：0-否，1-是',
  `published_at` datetime DEFAULT NULL COMMENT '发布时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_author_id` (`author_id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_series_id` (`series_id`),
  KEY `idx_status` (`status`),
  KEY `idx_published_at` (`published_at`),
  KEY `idx_is_top` (`is_top`),
  KEY `idx_is_recommend` (`is_recommend`),
  KEY `idx_view_count` (`view_count`),
  KEY `idx_created_at` (`created_at`),
  FULLTEXT KEY `ft_title_content` (`title`, `content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';

-- 文章标签关联表
CREATE TABLE IF NOT EXISTS `blog_article_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `tag_id` bigint NOT NULL COMMENT '标签ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_article_tag` (`article_id`, `tag_id`),
  KEY `idx_article_id` (`article_id`),
  KEY `idx_tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章标签关联表';

-- ==================== 相册相关表 ====================

-- 相册表
CREATE TABLE IF NOT EXISTS `blog_album` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '相册名称',
  `description` text DEFAULT NULL COMMENT '相册描述',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '封面图片URL',
  `author_id` bigint NOT NULL COMMENT '创建者ID',
  `photo_count` int NOT NULL DEFAULT 0 COMMENT '照片数量',
  `view_count` bigint NOT NULL DEFAULT 0 COMMENT '浏览次数',
  `status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_author_id` (`author_id`),
  KEY `idx_status` (`status`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='相册表';

-- 相册照片表
CREATE TABLE IF NOT EXISTS `blog_album_photo` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `album_id` bigint NOT NULL COMMENT '相册ID',
  `title` varchar(200) DEFAULT NULL COMMENT '照片标题',
  `description` text DEFAULT NULL COMMENT '照片描述',
  `image_url` varchar(500) NOT NULL COMMENT '图片URL',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序权重',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_album_id` (`album_id`),
  KEY `idx_sort_order` (`sort_order`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='相册照片表';

-- ==================== 留言板相关表 ====================

-- 留言板表
CREATE TABLE IF NOT EXISTS `blog_message_board` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '留言板名称',
  `description` text DEFAULT NULL COMMENT '留言板描述',
  `status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='留言板表';

-- 留言表
CREATE TABLE IF NOT EXISTS `blog_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `board_id` bigint NOT NULL COMMENT '留言板ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID（可为空，支持匿名留言）',
  `nickname` varchar(50) NOT NULL COMMENT '留言者昵称',
  `email` varchar(100) DEFAULT NULL COMMENT '留言者邮箱',
  `website` varchar(200) DEFAULT NULL COMMENT '留言者网站',
  `avatar` varchar(500) DEFAULT NULL COMMENT '留言者头像',
  `content` text NOT NULL COMMENT '留言内容',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `status` varchar(20) NOT NULL DEFAULT 'pending' COMMENT '状态：pending-待审核，approved-已通过，rejected-已拒绝',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_board_id` (`board_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='留言表';

-- ==================== 评论相关表 ====================

-- 评论表
CREATE TABLE IF NOT EXISTS `blog_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID（可为空，支持匿名评论）',
  `parent_id` bigint DEFAULT NULL COMMENT '父评论ID（用于回复功能）',
  `nickname` varchar(50) NOT NULL COMMENT '评论者昵称',
  `email` varchar(100) DEFAULT NULL COMMENT '评论者邮箱',
  `website` varchar(200) DEFAULT NULL COMMENT '评论者网站',
  `avatar` varchar(500) DEFAULT NULL COMMENT '评论者头像',
  `content` text NOT NULL COMMENT '评论内容',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `like_count` int NOT NULL DEFAULT 0 COMMENT '点赞次数',
  `status` varchar(20) NOT NULL DEFAULT 'pending' COMMENT '状态：pending-待审核，approved-已通过，rejected-已拒绝',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_article_id` (`article_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_status` (`status`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- ==================== 友链相关表 ====================

-- 友链表
CREATE TABLE IF NOT EXISTS `blog_friend_link` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '友链名称',
  `url` varchar(500) NOT NULL COMMENT '友链URL',
  `logo` varchar(500) DEFAULT NULL COMMENT '友链Logo',
  `description` varchar(200) DEFAULT NULL COMMENT '友链描述',
  `category` varchar(50) DEFAULT 'default' COMMENT '友链分类',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序权重',
  `status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '状态：active-启用，inactive-禁用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category`),
  KEY `idx_status` (`status`),
  KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='友链表';

-- ==================== 统计相关表 ====================

-- 文章浏览记录表
CREATE TABLE IF NOT EXISTS `blog_article_view_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `ip_address` varchar(50) NOT NULL COMMENT 'IP地址',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `referer` varchar(500) DEFAULT NULL COMMENT '来源页面',
  `viewed_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
  PRIMARY KEY (`id`),
  KEY `idx_article_id` (`article_id`),
  KEY `idx_ip_address` (`ip_address`),
  KEY `idx_viewed_at` (`viewed_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章浏览记录表';

-- 系统配置表
CREATE TABLE IF NOT EXISTS `blog_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `config_key` varchar(100) NOT NULL COMMENT '配置键',
  `config_value` text DEFAULT NULL COMMENT '配置值',
  `description` varchar(200) DEFAULT NULL COMMENT '配置描述',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_config_key` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统配置表';

-- ==================== 初始化数据 ====================

-- 插入默认留言板
INSERT INTO `blog_message_board` (`name`, `description`) VALUES
('默认留言板', '欢迎在这里留言交流！');

-- 插入默认友链分类
INSERT INTO `blog_friend_link` (`name`, `url`, `description`, `category`) VALUES
('示例友链', 'https://example.com', '这是一个示例友链', 'default');

-- 插入默认系统配置
INSERT INTO `blog_config` (`config_key`, `config_value`, `description`) VALUES
('site_name', '我的博客', '网站名称'),
('site_description', '分享技术，记录生活', '网站描述'),
('site_keywords', '博客,技术,生活', '网站关键词'),
('site_author', '博主', '网站作者'),
('comment_audit', 'true', '评论是否需要审核'),
('message_audit', 'true', '留言是否需要审核'),
('allow_anonymous_comment', 'true', '是否允许匿名评论'),
('allow_anonymous_message', 'true', '是否允许匿名留言');