package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.entity.AiPromptTemplate;
import com.exam.vo.PromptTemplateVo;

import java.util.List;
import java.util.Map;

/**
 * 提示词模板管理Service接口 // Prompt Template Management Service Interface
 * 提供提示词的CRUD、版本控制、变量替换等功能 // Provides CRUD, versioning, variable replacement, etc.
 * 
 * @author System
 * @date 2025-11-12
 */
public interface PromptTemplateService extends IService<AiPromptTemplate> {

    /**
     * 获取所有模板列表 // Get all template list
     * @return 模板VO列表 // Template VO list
     */
    List<PromptTemplateVo> listAllTemplates();

    /**
     * 根据分类获取模板 // Get templates by category
     * @param category 分类名称 // Category name
     * @return 模板VO列表 // Template VO list
     */
    List<PromptTemplateVo> listTemplatesByCategory(String category);

    /**
     * 根据模板编码获取模板 // Get template by code
     * @param templateCode 模板编码 // Template code
     * @return 模板VO // Template VO
     */
    PromptTemplateVo getTemplateByCode(String templateCode);

    /**
     * 创建新模板 // Create new template
     * @param vo 模板VO // Template VO
     * @return 创建成功的模板ID // Created template ID
     */
    Long createTemplate(PromptTemplateVo vo);

    /**
     * 更新模板 // Update template
     * @param id 模板ID // Template ID
     * @param vo 模板VO // Template VO
     * @return 是否成功 // Success flag
     */
    Boolean updateTemplate(Long id, PromptTemplateVo vo);

    /**
     * 删除模板(逻辑删除) // Delete template (soft delete)
     * @param id 模板ID // Template ID
     * @return 是否成功 // Success flag
     */
    Boolean deleteTemplate(Long id);

    /**
     * 启用/禁用模板 // Enable/Disable template
     * @param id 模板ID // Template ID
     * @param isActive 是否启用 // Active status
     * @return 是否成功 // Success flag
     */
    Boolean toggleTemplateStatus(Long id, Boolean isActive);

    /**
     * 渲染模板(替换变量) // Render template (replace variables)
     * @param templateCode 模板编码 // Template code
     * @param variables 变量映射 // Variable map
     * @return 渲染后的提示词 // Rendered prompt
     */
    String renderTemplate(String templateCode, Map<String, Object> variables);

    /**
     * 获取模板使用统计 // Get template usage statistics
     * @param templateCode 模板编码 // Template code
     * @return 统计信息 // Statistics info
     */
    Map<String, Object> getTemplateStatistics(String templateCode);

    /**
     * 批量导入模板 // Batch import templates
     * @param templates 模板列表 // Template list
     * @return 导入数量 // Imported count
     */
    Integer batchImportTemplates(List<PromptTemplateVo> templates);

    /**
     * 复制模板创建新版本 // Clone template to create new version
     * @param id 原模板ID // Original template ID
     * @param newVersion 新版本号 // New version number
     * @return 新模板ID // New template ID
     */
    Long cloneTemplate(Long id, String newVersion);
}