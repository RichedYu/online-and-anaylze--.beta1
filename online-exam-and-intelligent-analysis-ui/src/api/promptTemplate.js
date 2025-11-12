import request from "@/utils/request"; // 请求工具 // HTTP request helper

/**
 * 提示词模板管理API // Prompt Template Management API
 * 提供前端调用的所有接口 // Provides all APIs for frontend calls
 */

// ==================== 模板CRUD操作 // Template CRUD Operations ====================

/**
 * 获取所有提示词模板 // Get all prompt templates
 */
export const fetchAllTemplates = () => {
  return request.get("/login/prompt-templates/list");
};

/**
 * 根据分类获取模板 // Get templates by category
 * @param {string} category - 分类名称 // Category name
 */
export const fetchTemplatesByCategory = (category) => {
  return request.get(`/login/prompt-templates/category/${category}`);
};

/**
 * 根据模板编码获取详情 // Get template details by code
 * @param {string} templateCode - 模板编码 // Template code
 */
export const fetchTemplateByCode = (templateCode) => {
  return request.get(`/login/prompt-templates/${templateCode}`);
};

/**
 * 创建新模板 // Create new template
 * @param {Object} templateData - 模板数据 // Template data
 */
export const createTemplate = (templateData) => {
  return request.post("/login/prompt-templates/create", templateData);
};

/**
 * 更新模板 // Update template
 * @param {number} id - 模板ID // Template ID
 * @param {Object} templateData - 模板数据 // Template data
 */
export const updateTemplate = (id, templateData) => {
  return request.put(`/login/prompt-templates/update/${id}`, templateData);
};

/**
 * 删除模板 // Delete template
 * @param {number} id - 模板ID // Template ID
 */
export const deleteTemplate = (id) => {
  return request.delete(`/login/prompt-templates/delete/${id}`);
};

/**
 * 启用/禁用模板 // Enable/Disable template
 * @param {number} id - 模板ID // Template ID
 * @param {boolean} isActive - 是否启用 // Is active
 */
export const toggleTemplateStatus = (id, isActive) => {
  return request.patch(`/login/prompt-templates/${id}/toggle`, null, {
    params: { isActive },
  });
};

// ==================== 模板操作 // Template Operations ====================

/**
 * 渲染模板(测试) // Render template (testing)
 * @param {string} templateCode - 模板编码 // Template code
 * @param {Object} variables - 变量映射 // Variable map
 */
export const renderTemplate = (templateCode, variables) => {
  return request.post(
    `/login/prompt-templates/${templateCode}/render`,
    variables
  );
};

/**
 * 获取模板统计信息 // Get template statistics
 * @param {string} templateCode - 模板编码 // Template code
 */
export const fetchTemplateStatistics = (templateCode) => {
  return request.get(`/login/prompt-templates/${templateCode}/statistics`);
};

/**
 * 批量导入模板 // Batch import templates
 * @param {Array} templates - 模板列表 // Template list
 */
export const batchImportTemplates = (templates) => {
  return request.post("/login/prompt-templates/batch-import", templates);
};

/**
 * 克隆模板创建新版本 // Clone template to create new version
 * @param {number} id - 原模板ID // Original template ID
 * @param {string} newVersion - 新版本号 // New version number
 */
export const cloneTemplate = (id, newVersion) => {
  return request.post(`/login/prompt-templates/${id}/clone`, null, {
    params: { newVersion },
  });
};

// ==================== 缓存管理 // Cache Management ====================

/**
 * 清除所有提示词缓存 // Clear all prompt caches
 */
export const clearAllCaches = () => {
  return request.delete("/login/prompt-templates/cache/clear");
};

/**
 * 获取缓存统计信息 // Get cache statistics
 */
export const fetchCacheStatistics = () => {
  return request.get("/login/prompt-templates/cache/statistics");
};

/**
 * 重置缓存统计 // Reset cache statistics
 */
export const resetCacheStatistics = () => {
  return request.post("/login/prompt-templates/cache/statistics/reset");
};

/**
 * 缓存预热 // Cache warming
 */
export const warmUpCache = () => {
  return request.post("/login/prompt-templates/cache/warm-up");
};

// ==================== 增强版AI分析接口 // Enhanced AI Analysis APIs ====================

/**
 * 增强版考试分析1 - 题型分析 // Enhanced Exam Analysis 1 - Question Type
 * @param {Array} questionTypeData - 题型数据 // Question type data
 * @param {string} modelCode - 模型编码(可选) // Model code (optional)
 */
export const examAnalysis1Enhanced = (questionTypeData, modelCode = null) => {
  return request.post("/login/ai-enhanced/examAnalysis1", questionTypeData, {
    params: { model: modelCode },
  });
};

/**
 * 增强版考试分析2 - 难度分析 // Enhanced Exam Analysis 2 - Difficulty
 * @param {Array} questionTypeData - 题型数据 // Question type data
 * @param {string} modelCode - 模型编码(可选) // Model code (optional)
 */
export const examAnalysis2Enhanced = (questionTypeData, modelCode = null) => {
  return request.post("/login/ai-enhanced/examAnalysis2", questionTypeData, {
    params: { model: modelCode },
  });
};

/**
 * 增强版考试分析3 - 知识点分析 // Enhanced Exam Analysis 3 - Knowledge Points
 * @param {Array} knowledgePointsData - 知识点数据 // Knowledge points data
 * @param {string} modelCode - 模型编码(可选) // Model code (optional)
 */
export const examAnalysis3Enhanced = (
  knowledgePointsData,
  modelCode = null
) => {
  return request.post("/login/ai-enhanced/examAnalysis3", knowledgePointsData, {
    params: { model: modelCode },
  });
};

/**
 * 增强版考试分析4 - 章节分析 // Enhanced Exam Analysis 4 - Chapter
 * @param {number} scoreId - 成绩ID // Score ID
 * @param {string} modelCode - 模型编码(可选) // Model code (optional)
 */
export const examAnalysis4Enhanced = (scoreId, modelCode = null) => {
  return request.get(`/login/ai-enhanced/examAnalysis4/${scoreId}`, {
    params: { model: modelCode },
  });
};

/**
 * 增强版AI对话 // Enhanced AI chat
 * @param {string} userInput - 用户输入 // User input
 * @param {string} modelCode - 模型编码(可选) // Model code (optional)
 * @param {string} templateCode - 模板编码(可选) // Template code (optional)
 * @param {Object} variables - 变量映射(可选) // Variable map (optional)
 */
export const chatEnhanced = (
  userInput,
  modelCode = null,
  templateCode = null,
  variables = null
) => {
  return request.get("/login/ai-enhanced/chat", {
    params: {
      userInput,
      model: modelCode,
      template: templateCode,
      variables,
    },
  });
};
