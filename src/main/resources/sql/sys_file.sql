-- SysFile表创建脚本
CREATE TABLE IF NOT EXISTS `sys_file` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `file_name` varchar(255) NOT NULL COMMENT '文件原始名称',
  `file_size` bigint NOT NULL COMMENT '文件大小（字节）',
  `mime_type` varchar(100) NOT NULL COMMENT '文件类型（如 image/jpeg,application/pdf）',
  `oss_url` varchar(500) NOT NULL COMMENT 'OSS 文件存储地址',
  `STATUS` varchar(20) NOT NULL DEFAULT 'unused' COMMENT '文件状态,使用used，未被使用unused',
  `upload_user_id` varchar(100) NOT NULL COMMENT '上传者用户ID',
  `upload_at` datetime NOT NULL COMMENT '文件上传时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_upload_user_id` (`upload_user_id`),
  KEY `idx_status` (`STATUS`),
  KEY `idx_upload_at` (`upload_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统文件表';