-- =====================================================
-- 智能推荐系统数据库表结构
-- Intelligent Recommendation System Database Schema
-- =====================================================
-- 功能 / Features:
-- 1. 学习资源推荐 / Learning Resource Recommendation
-- 2. 智能组卷 / Intelligent Paper Generation
-- 3. 推荐记录追踪 / Recommendation History Tracking
-- =====================================================

-- 1. 学习资源表 / Learning Resources Table
-- 存储各类学习资源信息 / Store various learning resource information
CREATE TABLE IF NOT EXISTS learning_resource (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键 / Primary key',
    resource_name VARCHAR(200) NOT NULL COMMENT '资源名称 / Resource name',
    resource_type VARCHAR(50) NOT NULL COMMENT '资源类型(VIDEO, DOCUMENT, EXERCISE, ARTICLE) / Resource type',
    subject_id BIGINT NOT NULL COMMENT '科目ID / Subject ID',
    knowledge_point_ids JSON COMMENT '关联知识点ID列表 / Related knowledge point IDs',
    difficulty_level VARCHAR(20) COMMENT '难度等级(EASY, MEDIUM, HARD) / Difficulty level',
    resource_url VARCHAR(500) COMMENT '资源链接 / Resource URL',
    description TEXT COMMENT '资源描述 / Resource description',
    tags JSON COMMENT '标签列表 / Tags list',
    duration_minutes INT COMMENT '学习时长(分钟) / Duration in minutes',
    view_count INT DEFAULT 0 COMMENT '浏览次数 / View count',
    like_count INT DEFAULT 0 COMMENT '点赞数 / Like count',
    effectiveness_score DECIMAL(3,2) DEFAULT 0.0 COMMENT '有效性评分(0-1) / Effectiveness score',
    is_active TINYINT(1) DEFAULT 1 COMMENT '是否启用 / Is active',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 / Created at',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 / Updated at',
    create_by BIGINT COMMENT '创建人 / Creator',
    update_by BIGINT COMMENT '更新人 / Updater',
    INDEX idx_subject_id (subject_id),
    INDEX idx_difficulty (difficulty_level),
    INDEX idx_resource_type (resource_type),
    INDEX idx_is_active (is_active)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学习资源表 / Learning Resource Table';

-- 2. 推荐记录表 / Recommendation History Table
-- 记录推荐历史和用户反馈 / Record recommendation history and user feedback
CREATE TABLE IF NOT EXISTS recommendation_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键 / Primary key',
    student_id BIGINT NOT NULL COMMENT '学生ID / Student ID',
    subject_id BIGINT NOT NULL COMMENT '科目ID / Subject ID',
    recommendation_type VARCHAR(50) NOT NULL COMMENT '推荐类型(RESOURCE, PAPER, KNOWLEDGE) / Recommendation type',
    resource_id BIGINT COMMENT '资源ID / Resource ID',
    paper_id BIGINT COMMENT '试卷ID / Paper ID',
    recommendation_reason TEXT COMMENT '推荐理由 / Recommendation reason',
    weak_points JSON COMMENT '薄弱知识点 / Weak knowledge points',
    algorithm_type VARCHAR(50) COMMENT '推荐算法(COLLABORATIVE, CONTENT_BASED, AI) / Algorithm type',
    confidence_score DECIMAL(3,2) COMMENT '推荐置信度 / Confidence score',
    is_accepted TINYINT(1) COMMENT '是否接受推荐 / Is accepted',
    feedback_score INT COMMENT '用户反馈评分(1-5) / User feedback score',
    feedback_comment VARCHAR(500) COMMENT '反馈评论 / Feedback comment',
    time_spent_minutes INT COMMENT '使用时长(分钟) / Time spent in minutes',
    is_completed TINYINT(1) DEFAULT 0 COMMENT '是否完成 / Is completed',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '推荐时间 / Recommended at',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 / Updated at',
    INDEX idx_student_id (student_id),
    INDEX idx_subject_id (subject_id),
    INDEX idx_recommendation_type (recommendation_type),
    INDEX idx_create_time (create_time),
    INDEX idx_is_accepted (is_accepted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推荐历史表 / Recommendation History Table';

-- 3. 学生资源学习记录表 / Student Resource Learning Record Table
-- 追踪学生对资源的学习进度 / Track student learning progress on resources
CREATE TABLE IF NOT EXISTS student_learning_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键 / Primary key',
    student_id BIGINT NOT NULL COMMENT '学生ID / Student ID',
    resource_id BIGINT NOT NULL COMMENT '资源ID / Resource ID',
    start_time DATETIME COMMENT '开始时间 / Start time',
    last_access_time DATETIME COMMENT '最后访问时间 / Last access time',
    total_duration_minutes INT DEFAULT 0 COMMENT '总学习时长(分钟) / Total duration in minutes',
    completion_rate DECIMAL(5,2) DEFAULT 0.0 COMMENT '完成率(%) / Completion rate',
    is_completed TINYINT(1) DEFAULT 0 COMMENT '是否完成 / Is completed',
    mastery_level VARCHAR(20) COMMENT '掌握程度(NOT_STARTED, LEARNING, MASTERED) / Mastery level',
    practice_count INT DEFAULT 0 COMMENT '练习次数 / Practice count',
    practice_accuracy DECIMAL(5,2) COMMENT '练习正确率(%) / Practice accuracy',
    notes TEXT COMMENT '学习笔记 / Learning notes',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 / Created at',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 / Updated at',
    UNIQUE KEY uk_student_resource (student_id, resource_id),
    INDEX idx_student_id (student_id),
    INDEX idx_resource_id (resource_id),
    INDEX idx_is_completed (is_completed)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生资源学习记录表 / Student Learning Record Table';

-- 4. 智能组卷配置表 / Adaptive Paper Configuration Table
-- 存储智能组卷的配置和历史 / Store adaptive paper generation configs and history
CREATE TABLE IF NOT EXISTS adaptive_paper_config (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键 / Primary key',
    config_name VARCHAR(200) NOT NULL COMMENT '配置名称 / Config name',
    student_id BIGINT COMMENT '目标学生ID(可为空表示模板) / Target student ID',
    subject_id BIGINT NOT NULL COMMENT '科目ID / Subject ID',
    target_difficulty VARCHAR(20) NOT NULL COMMENT '目标难度(EASY, MEDIUM, HARD, ADAPTIVE) / Target difficulty',
    question_distribution JSON COMMENT '题型分布 {"单选题":10, "多选题":5} / Question type distribution',
    knowledge_point_weights JSON COMMENT '知识点权重 / Knowledge point weights',
    total_score INT DEFAULT 100 COMMENT '总分 / Total score',
    time_limit_minutes INT COMMENT '时间限制(分钟) / Time limit in minutes',
    adaptation_strategy VARCHAR(50) COMMENT '自适应策略(IRT, CAT, KNOWLEDGE_GRAPH) / Adaptation strategy',
    include_weak_points TINYINT(1) DEFAULT 1 COMMENT '是否包含薄弱点 / Include weak points',
    weak_point_ratio DECIMAL(3,2) DEFAULT 0.6 COMMENT '薄弱点占比 / Weak point ratio',
    review_point_ratio DECIMAL(3,2) DEFAULT 0.3 COMMENT '复习点占比 / Review point ratio',
    challenge_ratio DECIMAL(3,2) DEFAULT 0.1 COMMENT '挑战题占比 / Challenge ratio',
    is_template TINYINT(1) DEFAULT 0 COMMENT '是否为模板 / Is template',
    usage_count INT DEFAULT 0 COMMENT '使用次数 / Usage count',
    avg_score DECIMAL(5,2) COMMENT '平均分 / Average score',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 / Created at',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 / Updated at',
    create_by BIGINT COMMENT '创建人 / Creator',
    INDEX idx_student_id (student_id),
    INDEX idx_subject_id (subject_id),
    INDEX idx_is_template (is_template)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能组卷配置表 / Adaptive Paper Config Table';

-- 5. 推荐算法性能统计表 / Recommendation Algorithm Performance Stats Table
-- 追踪不同推荐算法的效果 / Track performance of different recommendation algorithms
CREATE TABLE IF NOT EXISTS recommendation_algorithm_stats (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键 / Primary key',
    algorithm_type VARCHAR(50) NOT NULL COMMENT '算法类型 / Algorithm type',
    recommendation_type VARCHAR(50) NOT NULL COMMENT '推荐类型 / Recommendation type',
    subject_id BIGINT COMMENT '科目ID / Subject ID',
    total_recommendations INT DEFAULT 0 COMMENT '总推荐次数 / Total recommendations',
    accepted_count INT DEFAULT 0 COMMENT '接受次数 / Accepted count',
    acceptance_rate DECIMAL(5,2) COMMENT '接受率(%) / Acceptance rate',
    avg_feedback_score DECIMAL(3,2) COMMENT '平均反馈评分 / Average feedback score',
    avg_effectiveness_score DECIMAL(3,2) COMMENT '平均有效性评分 / Average effectiveness',
    avg_completion_rate DECIMAL(5,2) COMMENT '平均完成率 / Average completion rate',
    avg_improvement_score DECIMAL(5,2) COMMENT '平均提升分数 / Average improvement score',
    last_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间 / Last updated',
    UNIQUE KEY uk_algorithm_type_subject (algorithm_type, recommendation_type, subject_id),
    INDEX idx_algorithm_type (algorithm_type),
    INDEX idx_acceptance_rate (acceptance_rate DESC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推荐算法性能统计表 / Algorithm Performance Stats Table';

-- =====================================================
-- 初始化数据 / Initial Data
-- =====================================================

-- 插入示例学习资源 / Insert sample learning resources
INSERT INTO learning_resource (resource_name, resource_type, subject_id, knowledge_point_ids, difficulty_level, resource_url, description, tags, duration_minutes, effectiveness_score) VALUES
('高等数学微积分基础视频', 'VIDEO', 1, '[1, 2, 3]', 'EASY', 'https://example.com/calculus-basics', '详细讲解微积分的基本概念和计算方法', '["微积分", "基础", "视频教程"]', 45, 0.85),
('线性代数矩阵运算练习题', 'EXERCISE', 1, '[4, 5]', 'MEDIUM', 'https://example.com/linear-algebra-exercises', '50道矩阵运算练习题，含详细解析', '["线性代数", "矩阵", "练习题"]', 60, 0.78),
('概率论与数理统计PDF教材', 'DOCUMENT', 1, '[6, 7, 8]', 'MEDIUM', 'https://example.com/probability-stats.pdf', '经典概率论教材电子版', '["概率论", "数理统计", "教材"]', 120, 0.82),
('Java面向对象编程指南', 'ARTICLE', 2, '[10, 11]', 'EASY', 'https://example.com/java-oop-guide', '深入浅出讲解Java OOP核心概念', '["Java", "面向对象", "编程"]', 30, 0.88);

-- 插入智能组卷模板配置 / Insert adaptive paper templates
INSERT INTO adaptive_paper_config (config_name, subject_id, target_difficulty, question_distribution, total_score, time_limit_minutes, adaptation_strategy, is_template, include_weak_points, weak_point_ratio) VALUES
('数学基础自适应试卷', 1, 'ADAPTIVE', '{"单选题":10, "多选题":5, "填空题":5, "计算题":3}', 100, 120, 'IRT', 1, 1, 0.6),
('编程能力诊断试卷', 2, 'MEDIUM', '{"单选题":15, "多选题":5, "编程题":3}', 100, 90, 'KNOWLEDGE_GRAPH', 1, 1, 0.5);

-- 创建视图：学生推荐效果汇总 / Create view: Student recommendation effectiveness summary
CREATE OR REPLACE VIEW v_student_recommendation_effectiveness AS
SELECT 
    rh.student_id,
    rh.subject_id,
    rh.recommendation_type,
    COUNT(*) as total_recommendations,
    SUM(CASE WHEN rh.is_accepted = 1 THEN 1 ELSE 0 END) as accepted_count,
    ROUND(SUM(CASE WHEN rh.is_accepted = 1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) as acceptance_rate,
    AVG(rh.feedback_score) as avg_feedback_score,
    AVG(rh.confidence_score) as avg_confidence,
    SUM(CASE WHEN rh.is_completed = 1 THEN 1 ELSE 0 END) as completed_count,
    ROUND(SUM(CASE WHEN rh.is_completed = 1 THEN 1 ELSE 0 END) * 100.0 / NULLIF(SUM(CASE WHEN rh.is_accepted = 1 THEN 1 ELSE 0 END), 0), 2) as completion_rate
FROM recommendation_history rh
GROUP BY rh.student_id, rh.subject_id, rh.recommendation_type;

-- 创建存储过程：更新算法性能统计 / Create procedure: Update algorithm performance stats
DELIMITER $$

CREATE PROCEDURE update_recommendation_algorithm_stats()
BEGIN
    -- 更新推荐算法性能统计 / Update recommendation algorithm performance stats
    INSERT INTO recommendation_algorithm_stats (
        algorithm_type,
        recommendation_type,
        subject_id,
        total_recommendations,
        accepted_count,
        acceptance_rate,
        avg_feedback_score,
        avg_completion_rate
    )
    SELECT 
        algorithm_type,
        recommendation_type,
        subject_id,
        COUNT(*) as total_recommendations,
        SUM(CASE WHEN is_accepted = 1 THEN 1 ELSE 0 END) as accepted_count,
        ROUND(SUM(CASE WHEN is_accepted = 1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) as acceptance_rate,
        AVG(feedback_score) as avg_feedback_score,
        AVG(CASE WHEN is_accepted = 1 THEN 
            CASE WHEN is_completed = 1 THEN 100.0 ELSE 0 END 
        ELSE NULL END) as avg_completion_rate
    FROM recommendation_history
    WHERE create_time >= DATE_SUB(NOW(), INTERVAL 30 DAY)
    GROUP BY algorithm_type, recommendation_type, subject_id
    ON DUPLICATE KEY UPDATE
        total_recommendations = VALUES(total_recommendations),
        accepted_count = VALUES(accepted_count),
        acceptance_rate = VALUES(acceptance_rate),
        avg_feedback_score = VALUES(avg_feedback_score),
        avg_completion_rate = VALUES(avg_completion_rate),
        last_updated = NOW();
END$$

DELIMITER ;

-- =====================================================
-- 索引优化说明 / Index Optimization Notes
-- =====================================================
-- 1. 联合索引用于常见查询组合 / Composite indexes for common query combinations
-- 2. 外键索引用于关联查询 / Foreign key indexes for join queries  
-- 3. 时间索引用于范围查询 / Time indexes for range queries
-- 4. 状态索引用于过滤查询 / Status indexes for filter queries
-- =====================================================