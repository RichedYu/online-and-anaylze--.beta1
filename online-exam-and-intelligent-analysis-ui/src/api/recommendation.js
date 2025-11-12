/**
 * 智能推荐系统API
 * Intelligent Recommendation System API
 *
 * 提供学习资源推荐、自适应组卷、学习进度管理等功能
 * Provides learning resource recommendations, adaptive paper generation,
 * learning progress management and more
 *
 * @author System
 * @since 2025-11-12
 */

import request from "@/utils/request";

/**
 * 获取个性化学习资源推荐
 * Get personalized learning resource recommendations
 *
 * @param {Object} data - 推荐请求参数 / Recommendation request parameters
 * @param {Number} data.studentId - 学生ID / Student ID (required)
 * @param {Number} data.subjectId - 科目ID / Subject ID (required)
 * @param {Number} data.limit - 推荐数量 / Recommendation count (default: 5)
 * @param {String} data.algorithm - 推荐算法 / Algorithm (COLLABORATIVE|CONTENT_BASED|AI|HYBRID, default: AI)
 * @param {String} data.resourceType - 资源类型过滤 / Resource type filter (VIDEO|DOCUMENT|EXERCISE|ARTICLE)
 * @param {String} data.difficultyLevel - 难度级别 / Difficulty level (EASY|MEDIUM|HARD)
 * @param {Boolean} data.unlearnedOnly - 只推荐未学习的 / Only unlearned (default: false)
 * @param {Number} data.minEffectivenessScore - 最小有效性评分 / Min effectiveness score (0-1)
 * @returns {Promise} 推荐结果列表 / List of recommendation results
 */
export function getRecommendations(data) {
  return request({
    url: "/login/recommendation/resources",
    method: "post",
    data,
  });
}

/**
 * 生成自适应试卷
 * Generate adaptive paper
 *
 * @param {Number} studentId - 学生ID / Student ID
 * @param {Number} subjectId - 科目ID / Subject ID
 * @param {Object} config - 组卷配置 / Paper configuration
 * @param {String} config.configName - 配置名称 / Config name
 * @param {Number} config.totalQuestions - 总题数 / Total questions
 * @param {String} config.targetDifficulty - 目标难度 / Target difficulty (EASY|MEDIUM|HARD|ADAPTIVE)
 * @param {String} config.adaptationStrategy - 适应策略 / Strategy (WEAK_POINTS|BALANCED|CHALLENGE)
 * @param {Number} config.weakPointRatio - 薄弱点比例 / Weak point ratio (0-1)
 * @returns {Promise} 生成的试卷配置 / Generated paper configuration
 */
export function generateAdaptivePaper(studentId, subjectId, config) {
  return request({
    url: "/login/recommendation/adaptive-paper",
    method: "post",
    params: { studentId, subjectId },
    data: config,
  });
}

/**
 * 获取学生推荐历史
 * Get student recommendation history
 *
 * @param {Number} studentId - 学生ID / Student ID
 * @param {Number} subjectId - 科目ID (可选) / Subject ID (optional)
 * @param {Number} limit - 返回数量 / Limit (default: 10)
 * @returns {Promise} 推荐历史列表 / List of recommendation history
 */
export function getRecommendationHistory(
  studentId,
  subjectId = null,
  limit = 10
) {
  return request({
    url: `/login/recommendation/history/${studentId}`,
    method: "get",
    params: { subjectId, limit },
  });
}

/**
 * 提交推荐反馈
 * Submit recommendation feedback
 *
 * @param {Number} recommendationId - 推荐ID / Recommendation ID
 * @param {Number} feedbackScore - 反馈评分(1-5分) / Feedback score (1-5)
 * @param {String} feedbackComment - 反馈评论 / Feedback comment (optional)
 * @returns {Promise} 是否提交成功 / Whether submission was successful
 */
export function submitFeedback(
  recommendationId,
  feedbackScore,
  feedbackComment = ""
) {
  return request({
    url: "/login/recommendation/feedback",
    method: "post",
    params: { recommendationId, feedbackScore, feedbackComment },
  });
}

/**
 * 获取学生学习记录
 * Get student learning records
 *
 * @param {Number} studentId - 学生ID / Student ID
 * @param {Number} subjectId - 科目ID (可选) / Subject ID (optional)
 * @returns {Promise} 学习记录列表 / List of learning records
 */
export function getLearningRecords(studentId, subjectId = null) {
  return request({
    url: `/login/recommendation/learning-records/${studentId}`,
    method: "get",
    params: { subjectId },
  });
}

/**
 * 更新学习记录
 * Update learning record
 *
 * @param {Object} record - 学习记录对象 / Learning record object
 * @param {Number} record.studentId - 学生ID / Student ID
 * @param {Number} record.resourceId - 资源ID / Resource ID
 * @param {Number} record.subjectId - 科目ID / Subject ID
 * @param {Number} record.totalDurationMinutes - 学习时长(分钟) / Duration in minutes
 * @param {Number} record.completionRate - 完成率(0-1) / Completion rate (0-1)
 * @param {String} record.masteryLevel - 掌握程度 / Mastery level (NOT_STARTED|LEARNING|MASTERED)
 * @param {Number} record.practiceAccuracy - 练习正确率 / Practice accuracy (0-1)
 * @returns {Promise} 是否更新成功 / Whether update was successful
 */
export function updateLearningRecord(record) {
  return request({
    url: "/login/recommendation/learning-records",
    method: "post",
    data: record,
  });
}

/**
 * 分析学生薄弱知识点
 * Analyze student weak knowledge points
 *
 * @param {Number} studentId - 学生ID / Student ID
 * @param {Number} subjectId - 科目ID / Subject ID
 * @returns {Promise} 薄弱知识点及掌握度 / Weak knowledge points and mastery levels
 */
export function analyzeWeakPoints(studentId, subjectId) {
  return request({
    url: `/login/recommendation/weak-points/${studentId}/${subjectId}`,
    method: "get",
  });
}

/**
 * 获取热门学习资源
 * Get popular learning resources
 *
 * @param {Number} subjectId - 科目ID / Subject ID
 * @param {String} resourceType - 资源类型 (可选) / Resource type (optional)
 * @param {Number} limit - 返回数量 / Limit (default: 10)
 * @returns {Promise} 热门资源列表 / List of popular resources
 */
export function getPopularResources(
  subjectId,
  resourceType = null,
  limit = 10
) {
  return request({
    url: "/login/recommendation/popular-resources",
    method: "get",
    params: { subjectId, resourceType, limit },
  });
}

/**
 * 获取推荐算法统计信息
 * Get recommendation algorithm statistics
 *
 * @param {String} algorithmType - 算法类型 / Algorithm type (COLLABORATIVE|CONTENT_BASED|AI|HYBRID)
 * @param {String} startDate - 开始日期 / Start date (optional, format: YYYY-MM-DD)
 * @param {String} endDate - 结束日期 / End date (optional, format: YYYY-MM-DD)
 * @returns {Promise} 统计信息 / Statistics
 */
export function getAlgorithmStatistics(
  algorithmType,
  startDate = null,
  endDate = null
) {
  return request({
    url: "/login/recommendation/algorithm-stats",
    method: "get",
    params: { algorithmType, startDate, endDate },
  });
}

/**
 * 批量推荐资源（适用于班级推荐）
 * Batch recommend resources (for class recommendations)
 *
 * @param {Array<Number>} studentIds - 学生ID列表 / List of student IDs
 * @param {Number} subjectId - 科目ID / Subject ID
 * @param {Number} limit - 每个学生的推荐数量 / Number of recommendations per student (default: 5)
 * @returns {Promise} 学生ID到推荐结果的映射 / Map of student ID to recommendation results
 */
export function batchRecommend(studentIds, subjectId, limit = 5) {
  return request({
    url: "/login/recommendation/batch-recommend",
    method: "post",
    params: { studentIds, subjectId, limit },
  });
}

/**
 * 保存自适应组卷配置模板
 * Save adaptive paper configuration template
 *
 * @param {Object} config - 配置对象 / Configuration object
 * @param {String} config.configName - 配置名称 / Config name
 * @param {Number} config.subjectId - 科目ID / Subject ID
 * @param {Number} config.totalQuestions - 总题数 / Total questions
 * @param {String} config.targetDifficulty - 目标难度 / Target difficulty
 * @param {String} config.adaptationStrategy - 适应策略 / Adaptation strategy
 * @param {Number} config.weakPointRatio - 薄弱点比例 / Weak point ratio
 * @param {Boolean} config.isTemplate - 是否为模板 / Is template
 * @returns {Promise} 是否保存成功 / Whether saving was successful
 */
export function saveAdaptiveConfig(config) {
  return request({
    url: "/login/recommendation/adaptive-config",
    method: "post",
    data: config,
  });
}

/**
 * 获取配置模板列表
 * Get configuration template list
 *
 * @param {Number} subjectId - 科目ID (可选) / Subject ID (optional)
 * @param {Boolean} isTemplate - 是否只查询模板 / Whether to query only templates (optional)
 * @returns {Promise} 配置列表 / List of configurations
 */
export function getAdaptiveConfigs(subjectId = null, isTemplate = null) {
  return request({
    url: "/login/recommendation/adaptive-configs",
    method: "get",
    params: { subjectId, isTemplate },
  });
}

// ========== 常量定义 / Constants ==========

/**
 * 推荐算法类型
 * Recommendation Algorithm Types
 */
export const ALGORITHM_TYPES = {
  COLLABORATIVE: {
    value: "COLLABORATIVE",
    label: "协同过滤 / Collaborative Filtering",
  },
  CONTENT_BASED: { value: "CONTENT_BASED", label: "基于内容 / Content-Based" },
  AI: { value: "AI", label: "AI推荐 / AI-Powered" },
  HYBRID: { value: "HYBRID", label: "混合推荐 / Hybrid" },
};

/**
 * 资源类型
 * Resource Types
 */
export const RESOURCE_TYPES = {
  VIDEO: { value: "VIDEO", label: "视频 / Video", icon: "video-camera" },
  DOCUMENT: { value: "DOCUMENT", label: "文档 / Document", icon: "file-text" },
  EXERCISE: { value: "EXERCISE", label: "练习题 / Exercise", icon: "edit" },
  ARTICLE: { value: "ARTICLE", label: "文章 / Article", icon: "read" },
  INTERACTIVE: {
    value: "INTERACTIVE",
    label: "互动课程 / Interactive",
    icon: "interaction",
  },
};

/**
 * 难度级别
 * Difficulty Levels
 */
export const DIFFICULTY_LEVELS = {
  EASY: { value: "EASY", label: "简单 / Easy", color: "#52c41a" },
  MEDIUM: { value: "MEDIUM", label: "中等 / Medium", color: "#1890ff" },
  HARD: { value: "HARD", label: "困难 / Hard", color: "#f5222d" },
  ADAPTIVE: { value: "ADAPTIVE", label: "自适应 / Adaptive", color: "#722ed1" },
};

/**
 * 掌握程度
 * Mastery Levels
 */
export const MASTERY_LEVELS = {
  NOT_STARTED: {
    value: "NOT_STARTED",
    label: "未开始 / Not Started",
    color: "#d9d9d9",
  },
  LEARNING: { value: "LEARNING", label: "学习中 / Learning", color: "#1890ff" },
  MASTERED: { value: "MASTERED", label: "已掌握 / Mastered", color: "#52c41a" },
};

/**
 * 适应策略
 * Adaptation Strategies
 */
export const ADAPTATION_STRATEGIES = {
  WEAK_POINTS: {
    value: "WEAK_POINTS",
    label: "薄弱点强化 / Weak Points Focus",
  },
  BALANCED: { value: "BALANCED", label: "均衡提升 / Balanced" },
  CHALLENGE: { value: "CHALLENGE", label: "挑战进阶 / Challenge" },
};

/**
 * 默认导出所有API函数
 * Default export all API functions
 */
export default {
  getRecommendations,
  generateAdaptivePaper,
  getRecommendationHistory,
  submitFeedback,
  getLearningRecords,
  updateLearningRecord,
  analyzeWeakPoints,
  getPopularResources,
  getAlgorithmStatistics,
  batchRecommend,
  saveAdaptiveConfig,
  getAdaptiveConfigs,
  // Constants
  ALGORITHM_TYPES,
  RESOURCE_TYPES,
  DIFFICULTY_LEVELS,
  MASTERY_LEVELS,
  ADAPTATION_STRATEGIES,
};
