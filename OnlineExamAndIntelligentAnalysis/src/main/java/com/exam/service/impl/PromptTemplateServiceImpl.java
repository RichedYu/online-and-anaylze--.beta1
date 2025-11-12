package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.AiPromptTemplate;
import com.exam.exception.ExamException;
import com.exam.mapper.AiPromptTemplateMapper;
import com.exam.service.PromptTemplateService;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.PromptTemplateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 提示词模板管理Service实现类 // Prompt Template Management Service Implementation
 * 实现提示词的CRUD、缓存、变量替换等核心功能 // Implements CRUD, caching, variable replacement, etc.
 * 
 * @author System
 * @date 2025-11-12
 */
@Slf4j
@Service
public class PromptTemplateServiceImpl extends ServiceImpl<AiPromptTemplateMapper, AiPromptTemplate> 
    implements PromptTemplateService {

    // 变量占位符正则表达式 {{variable}} // Variable placeholder regex pattern
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{\\{(\\w+)\\}\\}");

    /**
     * 获取所有模板列表 // Get all template list
     * 使用缓存提升性能 // Use cache for performance
     */
    @Override
    @Cacheable(value = "ai:prompts", key = "'all'", cacheManager = "aiCacheManager")
    public List<PromptTemplateVo> listAllTemplates() {
        log.info("查询所有提示词模板 // Querying all prompt templates");
        
        LambdaQueryWrapper<AiPromptTemplate> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(AiPromptTemplate::getCreateTime);
        
        List<AiPromptTemplate> templates = this.list(wrapper);
        return templates.stream()
            .map(this::convertToVo)
            .collect(Collectors.toList());
    }

    /**
     * 根据分类获取模板 // Get templates by category
     */
    @Override
    @Cacheable(value = "ai:prompts", key = "'category:' + #category", cacheManager = "aiCacheManager")
    public List<PromptTemplateVo> listTemplatesByCategory(String category) {
        log.info("查询分类[{}]的提示词模板 // Querying templates for category [{}]", category, category);
        
        LambdaQueryWrapper<AiPromptTemplate> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AiPromptTemplate::getCategory, category)
               .eq(AiPromptTemplate::getIsActive, true)
               .orderByDesc(AiPromptTemplate::getUsageCount);
        
        List<AiPromptTemplate> templates = this.list(wrapper);
        return templates.stream()
            .map(this::convertToVo)
            .collect(Collectors.toList());
    }

    /**
     * 根据模板编码获取模板 // Get template by code
     * 热点数据使用长期缓存 // Hot data uses long-term cache
     */
    @Override
    @Cacheable(value = "ai:hot", key = "'template:' + #templateCode", cacheManager = "aiCacheManager")
    public PromptTemplateVo getTemplateByCode(String templateCode) {
        log.info("查询模板编码[{}] // Querying template code [{}]", templateCode, templateCode);
        
        LambdaQueryWrapper<AiPromptTemplate> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AiPromptTemplate::getTemplateCode, templateCode)
               .eq(AiPromptTemplate::getIsActive, true);
        
        AiPromptTemplate template = this.getOne(wrapper);
        if (template == null) {
            log.warn("模板编码[{}]不存在 // Template code [{}] not found", templateCode, templateCode);
            throw new ExamException(ResultCodeEnum.DATA_ERROR);
        }
        
        return convertToVo(template);
    }

    /**
     * 创建新模板 // Create new template
     * 清除相关缓存 // Evict related caches
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {"ai:prompts", "ai:hot"}, allEntries = true, cacheManager = "aiCacheManager")
    public Long createTemplate(PromptTemplateVo vo) {
        log.info("创建新模板[{}] // Creating new template [{}]", vo.getTemplateName(), vo.getTemplateName());
        
        // 检查模板编码是否重复 // Check if template code already exists
        LambdaQueryWrapper<AiPromptTemplate> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AiPromptTemplate::getTemplateCode, vo.getTemplateCode());
        if (this.count(wrapper) > 0) {
            log.error("模板编码[{}]已存在 // Template code [{}] already exists", 
                vo.getTemplateCode(), vo.getTemplateCode());
            throw new ExamException(ResultCodeEnum.DATA_ERROR);
        }
        
        AiPromptTemplate template = convertToEntity(vo);
        template.setUsageCount(0L);
        template.setAvgResponseTime(0);
        
        this.save(template);
        log.info("模板创建成功，ID: {} // Template created successfully, ID: {}", 
            template.getId(), template.getId());
        
        return template.getId();
    }

    /**
     * 更新模板 // Update template
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {"ai:prompts", "ai:hot"}, allEntries = true, cacheManager = "aiCacheManager")
    public Boolean updateTemplate(Long id, PromptTemplateVo vo) {
        log.info("更新模板ID[{}] // Updating template ID [{}]", id, id);
        
        AiPromptTemplate existing = this.getById(id);
        if (existing == null) {
            log.error("模板ID[{}]不存在 // Template ID [{}] not found", id, id);
            throw new ExamException(ResultCodeEnum.DATA_ERROR);
        }
        
        // 如果修改了模板编码，检查新编码是否重复 // Check new code if changed
        if (!existing.getTemplateCode().equals(vo.getTemplateCode())) {
            LambdaQueryWrapper<AiPromptTemplate> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AiPromptTemplate::getTemplateCode, vo.getTemplateCode())
                   .ne(AiPromptTemplate::getId, id);
            if (this.count(wrapper) > 0) {
                log.error("模板编码[{}]已存在 // Template code [{}] already exists", 
                    vo.getTemplateCode(), vo.getTemplateCode());
                throw new ExamException(ResultCodeEnum.DATA_ERROR);
            }
        }
        
        AiPromptTemplate template = convertToEntity(vo);
        template.setId(id);
        // 保留统计数据 // Preserve statistics
        template.setUsageCount(existing.getUsageCount());
        template.setAvgResponseTime(existing.getAvgResponseTime());
        template.setLastUsedAt(existing.getLastUsedAt());
        
        boolean result = this.updateById(template);
        log.info("模板更新{}，ID: {} // Template update {}, ID: {}", 
            result ? "成功" : "失败", id, result ? "success" : "failed", id);
        
        return result;
    }

    /**
     * 删除模板(逻辑删除) // Delete template (soft delete)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {"ai:prompts", "ai:hot"}, allEntries = true, cacheManager = "aiCacheManager")
    public Boolean deleteTemplate(Long id) {
        log.info("删除模板ID[{}] // Deleting template ID [{}]", id, id);
        
        boolean result = this.removeById(id);
        log.info("模板删除{}，ID: {} // Template deletion {}, ID: {}", 
            result ? "成功" : "失败", id, result ? "success" : "failed", id);
        
        return result;
    }

    /**
     * 启用/禁用模板 // Enable/Disable template
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {"ai:prompts", "ai:hot"}, allEntries = true, cacheManager = "aiCacheManager")
    public Boolean toggleTemplateStatus(Long id, Boolean isActive) {
        log.info("切换模板ID[{}]状态为: {} // Toggle template ID [{}] status to: {}", 
            id, isActive, id, isActive);
        
        AiPromptTemplate template = this.getById(id);
        if (template == null) {
            log.error("模板ID[{}]不存在 // Template ID [{}] not found", id, id);
            throw new ExamException(ResultCodeEnum.DATA_ERROR);
        }
        
        template.setIsActive(isActive);
        boolean result = this.updateById(template);
        
        log.info("模板状态切换{}，ID: {} // Template status toggle {}, ID: {}", 
            result ? "成功" : "失败", id, result ? "success" : "failed", id);
        
        return result;
    }

    /**
     * 渲染模板(替换变量) // Render template (replace variables)
     * 核心功能：使用正则表达式替换{{variable}}占位符 // Core: Replace {{variable}} placeholders using regex
     */
    @Override
    public String renderTemplate(String templateCode, Map<String, Object> variables) {
        log.info("渲染模板[{}]，变量数: {} // Rendering template [{}], variable count: {}", 
            templateCode, variables != null ? variables.size() : 0, 
            templateCode, variables != null ? variables.size() : 0);
        
        long startTime = System.currentTimeMillis();
        
        try {
            // 获取模板内容 // Get template content
            PromptTemplateVo template = this.getTemplateByCode(templateCode);
            String content = template.getTemplateContent();
            
            // 替换变量 // Replace variables
            if (variables != null && !variables.isEmpty()) {
                Matcher matcher = VARIABLE_PATTERN.matcher(content);
                StringBuffer sb = new StringBuffer();
                
                while (matcher.find()) {
                    String variableName = matcher.group(1);
                    Object value = variables.get(variableName);
                    
                    if (value != null) {
                        // 转义特殊字符 // Escape special characters
                        String replacement = Matcher.quoteReplacement(value.toString());
                        matcher.appendReplacement(sb, replacement);
                        log.debug("替换变量 {{{}}} = {} // Replaced variable {{{}}} = {}", 
                            variableName, value, variableName, value);
                    } else {
                        log.warn("变量{{{}}}未提供值 // Variable {{{}}} value not provided", 
                            variableName, variableName);
                        matcher.appendReplacement(sb, matcher.group(0)); // 保留原占位符 // Keep original placeholder
                    }
                }
                matcher.appendTail(sb);
                content = sb.toString();
            }
            
            // 更新使用统计 // Update usage statistics
            long responseTime = System.currentTimeMillis() - startTime;
            this.updateTemplateStatistics(templateCode, (int) responseTime);
            
            log.info("模板渲染完成，耗时: {}ms // Template rendering completed, time: {}ms", 
                responseTime, responseTime);
            
            return content;
            
        } catch (Exception e) {
            log.error("模板渲染失败 // Template rendering failed: {}", e.getMessage(), e);
            throw new ExamException(ResultCodeEnum.FAIL);
        }
    }

    /**
     * 获取模板使用统计 // Get template usage statistics
     */
    @Override
    public Map<String, Object> getTemplateStatistics(String templateCode) {
        log.info("查询模板[{}]统计信息 // Querying template [{}] statistics", 
            templateCode, templateCode);
        
        LambdaQueryWrapper<AiPromptTemplate> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AiPromptTemplate::getTemplateCode, templateCode);
        
        AiPromptTemplate template = this.getOne(wrapper);
        if (template == null) {
            throw new ExamException(ResultCodeEnum.DATA_ERROR);
        }
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("templateCode", template.getTemplateCode());
        stats.put("templateName", template.getTemplateName());
        stats.put("usageCount", template.getUsageCount());
        stats.put("avgResponseTime", template.getAvgResponseTime() + "ms");
        stats.put("lastUsedAt", template.getLastUsedAt());
        stats.put("isActive", template.getIsActive());
        
        return stats;
    }

    /**
     * 批量导入模板 // Batch import templates
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {"ai:prompts", "ai:hot"}, allEntries = true, cacheManager = "aiCacheManager")
    public Integer batchImportTemplates(List<PromptTemplateVo> templates) {
        log.info("批量导入模板，数量: {} // Batch importing templates, count: {}", 
            templates.size(), templates.size());
        
        int successCount = 0;
        for (PromptTemplateVo vo : templates) {
            try {
                this.createTemplate(vo);
                successCount++;
            } catch (Exception e) {
                log.error("导入模板[{}]失败 // Failed to import template [{}]: {}", 
                    vo.getTemplateCode(), vo.getTemplateCode(), e.getMessage());
            }
        }
        
        log.info("批量导入完成，成功: {}/{}  // Batch import completed, success: {}/{}", 
            successCount, templates.size(), successCount, templates.size());
        
        return successCount;
    }

    /**
     * 复制模板创建新版本 // Clone template to create new version
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {"ai:prompts", "ai:hot"}, allEntries = true, cacheManager = "aiCacheManager")
    public Long cloneTemplate(Long id, String newVersion) {
        log.info("克隆模板ID[{}]，新版本: {} // Cloning template ID [{}], new version: {}", 
            id, newVersion, id, newVersion);
        
        AiPromptTemplate original = this.getById(id);
        if (original == null) {
            throw new ExamException(ResultCodeEnum.DATA_ERROR);
        }
        
        AiPromptTemplate cloned = new AiPromptTemplate();
        BeanUtils.copyProperties(original, cloned);
        cloned.setId(null);
        cloned.setVersion(newVersion);
        cloned.setTemplateCode(original.getTemplateCode() + "_v" + newVersion);
        cloned.setUsageCount(0L);
        cloned.setAvgResponseTime(0);
        cloned.setLastUsedAt(null);
        
        this.save(cloned);
        log.info("模板克隆成功，新ID: {} // Template cloned successfully, new ID: {}", 
            cloned.getId(), cloned.getId());
        
        return cloned.getId();
    }

    /**
     * 更新模板统计信息 // Update template statistics
     * 私有方法，异步更新不影响主流程 // Private method, async update doesn't affect main flow
     */
    private void updateTemplateStatistics(String templateCode, Integer responseTime) {
        try {
            baseMapper.incrementUsageCount(templateCode);
            baseMapper.updateAvgResponseTime(templateCode, responseTime);
        } catch (Exception e) {
            log.error("更新模板统计失败 // Failed to update template statistics: {}", e.getMessage());
            // 不抛出异常，避免影响主流程 // Don't throw exception to avoid affecting main flow
        }
    }

    /**
     * Entity转VO // Convert entity to VO
     */
    private PromptTemplateVo convertToVo(AiPromptTemplate entity) {
        PromptTemplateVo vo = new PromptTemplateVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    /**
     * VO转Entity // Convert VO to entity
     */
    private AiPromptTemplate convertToEntity(PromptTemplateVo vo) {
        AiPromptTemplate entity = new AiPromptTemplate();
        BeanUtils.copyProperties(vo, entity);
        if (!StringUtils.hasText(entity.getVersion())) {
            entity.setVersion("1.0.0");
        }
        if (entity.getIsActive() == null) {
            entity.setIsActive(true);
        }
        return entity;
    }
}