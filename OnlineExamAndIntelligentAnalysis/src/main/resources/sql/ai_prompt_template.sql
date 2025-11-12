-- =====================================================
-- 提示词模板管理表 // AI Prompt Template Management Table
-- =====================================================
CREATE TABLE IF NOT EXISTS `ai_prompt_template` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID // Primary key',
    `template_name` VARCHAR(100) NOT NULL COMMENT '模板名称 // Template name',
    `template_code` VARCHAR(50) NOT NULL COMMENT '模板编码(唯一标识) // Template code (unique identifier)',
    `template_content` TEXT NOT NULL COMMENT '模板内容(支持变量占位符) // Template content (supports variable placeholders)',
    `category` VARCHAR(50) DEFAULT 'GENERAL' COMMENT '分类(EXAM_ANALYSIS/RECOMMENDATION/PREDICTION等) // Category',
    `variables` JSON COMMENT '变量定义(JSON格式) // Variable definitions (JSON format)',
    `version` VARCHAR(20) DEFAULT '1.0.0' COMMENT '版本号 // Version number',
    `description` VARCHAR(500) COMMENT '模板描述 // Template description',
    `is_active` TINYINT(1) DEFAULT 1 COMMENT '是否启用(0:禁用 1:启用) // Active status (0:disabled 1:enabled)',
    `usage_count` BIGINT DEFAULT 0 COMMENT '使用次数统计 // Usage count statistics',
    `avg_response_time` INT DEFAULT 0 COMMENT '平均响应时间(ms) // Average response time (ms)',
    `last_used_at` DATETIME COMMENT '最后使用时间 // Last used timestamp',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 // Creation time',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 // Update time',
    `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '逻辑删除(0:未删除 1:已删除) // Logical delete flag',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_template_code` (`template_code`),
    KEY `idx_category` (`category`),
    KEY `idx_active` (`is_active`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI提示词模板表 // AI Prompt Template Table';

-- =====================================================
-- AI响应缓存统计表 // AI Response Cache Statistics Table
-- =====================================================
CREATE TABLE IF NOT EXISTS `ai_cache_statistics` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID // Primary key',
    `cache_key_hash` VARCHAR(64) NOT NULL COMMENT '缓存键哈希值 // Cache key hash',
    `prompt_template_code` VARCHAR(50) COMMENT '关联提示词模板 // Associated prompt template',
    `hit_count` BIGINT DEFAULT 0 COMMENT '缓存命中次数 // Cache hit count',
    `miss_count` BIGINT DEFAULT 0 COMMENT '缓存未命中次数 // Cache miss count',
    `avg_response_time` INT DEFAULT 0 COMMENT '平均响应时间(ms) // Average response time (ms)',
    `last_hit_at` DATETIME COMMENT '最后命中时间 // Last hit timestamp',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 // Creation time',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 // Update time',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_cache_key_hash` (`cache_key_hash`),
    KEY `idx_template_code` (`prompt_template_code`),
    KEY `idx_hit_count` (`hit_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI缓存统计表 // AI Cache Statistics Table';

-- =====================================================
-- 初始化默认提示词模板 // Initialize Default Prompt Templates
-- =====================================================
INSERT INTO `ai_prompt_template` 
(`template_name`, `template_code`, `template_content`, `category`, `variables`, `version`, `description`, `is_active`)
VALUES
('考试题型分析', 'EXAM_ANALYSIS_TYPE', '通过{{data}}分析这次考试的成绩，用{{wordCount}}字的篇幅总结：直接说优缺点，该如何做，不要重述我的数据，用''你''称呼学生，以本次考试中，你在为开头回答', 'EXAM_ANALYSIS', 
 '{"data": "题型数据", "wordCount": "250"}', '1.0.0', '分析学生在不同题型上的表现', 1),

('考试难度分析', 'EXAM_ANALYSIS_DIFFICULTY', '通过{{data}}分析这次考试难易程度及本次考试的成绩及建议，用{{wordCount}}字的篇幅总结：直接说优缺点，该如何做，不要重述我的数据，用''你''称呼学生，以本次考试中，你在为开头回答', 'EXAM_ANALYSIS',
 '{"data": "题型数据", "wordCount": "250"}', '1.0.0', '分析考试难度和提供改进建议', 1),

('知识点掌握分析', 'EXAM_ANALYSIS_KNOWLEDGE', '通过{{data}}分析学生的知识点掌握情况，用{{wordCount}}字的篇幅总结：直接说优缺点，薄弱知识点，该如何做，不要重述我的数据，用''你''称呼学生，以本科目中，你在为开头回答', 'EXAM_ANALYSIS',
 '{"data": "知识点数据", "wordCount": "400"}', '1.0.0', '分析学生知识点掌握情况和薄弱环节', 1),

('章节得分率分析', 'EXAM_ANALYSIS_CHAPTER', '通过本次考试试题所属章节占比：{{chapterData}}，和试题所属章节得分率：{{scoreRate}}分析学生的考试情况，用{{wordCount}}字的篇幅总结：直接说优缺点，该如何做，不要重述我的数据，用''你''称呼学生，以本次考试中，你在为开头回答', 'EXAM_ANALYSIS',
 '{"chapterData": "章节占比", "scoreRate": "得分率数据", "wordCount": "250"}', '1.0.0', '分析章节得分率和学习重点', 1);