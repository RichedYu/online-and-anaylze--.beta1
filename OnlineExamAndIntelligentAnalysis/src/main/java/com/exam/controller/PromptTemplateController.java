package com.exam.controller;

import com.exam.config.AiCacheConfig;
import com.exam.service.PromptTemplateService;
import com.exam.utils.result.Result;
import com.exam.vo.PromptTemplateVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 提示词模板管理Controller // Prompt Template Management Controller
 * 提供前端页面管理提示词的所有接口 // Provides all APIs for frontend prompt management
 * 
 * 功能包括: // Features include:
 * - CRUD操作 // CRUD operations
 * - 模板渲染和变量替换 // Template rendering and variable replacement
 * - 统计信息查询 // Statistics query
 * - 缓存管理 // Cache management
 * 
 * @author System
 * @date 2025-11-12
 */
@Slf4j
@Tag(name = "提示词模板管理 // Prompt Template Management")
@RestController
@RequestMapping("/login/prompt-templates")
public class PromptTemplateController {

    @Resource
    private PromptTemplateService promptTemplateService;

    @Resource(name = "aiCacheManager")
    private CacheManager cacheManager;

    @Resource
    private AiCacheConfig.CacheStatistics cacheStatistics;

    /**
     * 获取所有提示词模板 // Get all prompt templates
     */
    @Operation(summary = "获取所有提示词模板 // Get all prompt templates")
    @GetMapping("/list")
    public Result<List<PromptTemplateVo>> listAllTemplates() {
        log.info("API调用: 获取所有提示词模板 // API call: Get all prompt templates");
        List<PromptTemplateVo> templates = promptTemplateService.listAllTemplates();
        return Result.ok(templates);
    }

    /**
     * 根据分类获取模板 // Get templates by category
     */
    @Operation(summary = "根据分类获取模板 // Get templates by category")
    @GetMapping("/category/{category}")
    public Result<List<PromptTemplateVo>> listTemplatesByCategory(
            @Parameter(description = "分类名称 // Category name") 
            @PathVariable("category") String category) {
        log.info("API调用: 获取分类[{}]的模板 // API call: Get templates for category [{}]", 
            category, category);
        List<PromptTemplateVo> templates = promptTemplateService.listTemplatesByCategory(category);
        return Result.ok(templates);
    }

    /**
     * 根据模板编码获取模板详情 // Get template details by code
     */
    @Operation(summary = "根据模板编码获取详情 // Get template details by code")
    @GetMapping("/{templateCode}")
    public Result<PromptTemplateVo> getTemplateByCode(
            @Parameter(description = "模板编码 // Template code") 
            @PathVariable("templateCode") String templateCode) {
        log.info("API调用: 获取模板[{}]详情 // API call: Get template [{}] details", 
            templateCode, templateCode);
        PromptTemplateVo template = promptTemplateService.getTemplateByCode(templateCode);
        return Result.ok(template);
    }

    /**
     * 创建新模板 // Create new template
     */
    @Operation(summary = "创建新模板 // Create new template")
    @PostMapping("/create")
    public Result<Long> createTemplate(@RequestBody PromptTemplateVo vo) {
        log.info("API调用: 创建新模板[{}] // API call: Create new template [{}]", 
            vo.getTemplateName(), vo.getTemplateName());
        Long id = promptTemplateService.createTemplate(vo);
        return Result.ok(id);
    }

    /**
     * 更新模板 // Update template
     */
    @Operation(summary = "更新模板 // Update template")
    @PutMapping("/update/{id}")
    public Result<Boolean> updateTemplate(
            @Parameter(description = "模板ID // Template ID") 
            @PathVariable("id") Long id,
            @RequestBody PromptTemplateVo vo) {
        log.info("API调用: 更新模板ID[{}] // API call: Update template ID [{}]", id, id);
        Boolean result = promptTemplateService.updateTemplate(id, vo);
        return Result.ok(result);
    }

    /**
     * 删除模板 // Delete template
     */
    @Operation(summary = "删除模板 // Delete template")
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> deleteTemplate(
            @Parameter(description = "模板ID // Template ID") 
            @PathVariable("id") Long id) {
        log.info("API调用: 删除模板ID[{}] // API call: Delete template ID [{}]", id, id);
        Boolean result = promptTemplateService.deleteTemplate(id);
        return Result.ok(result);
    }

    /**
     * 启用/禁用模板 // Enable/Disable template
     */
    @Operation(summary = "启用/禁用模板 // Enable/Disable template")
    @PatchMapping("/{id}/toggle")
    public Result<Boolean> toggleTemplateStatus(
            @Parameter(description = "模板ID // Template ID") 
            @PathVariable("id") Long id,
            @Parameter(description = "是否启用 // Is active") 
            @RequestParam("isActive") Boolean isActive) {
        log.info("API调用: 切换模板ID[{}]状态为{} // API call: Toggle template ID [{}] status to {}", 
            id, isActive, id, isActive);
        Boolean result = promptTemplateService.toggleTemplateStatus(id, isActive);
        return Result.ok(result);
    }

    /**
     * 渲染模板(测试用) // Render template (for testing)
     */
    @Operation(summary = "渲染模板(测试) // Render template (testing)")
    @PostMapping("/{templateCode}/render")
    public Result<String> renderTemplate(
            @Parameter(description = "模板编码 // Template code") 
            @PathVariable("templateCode") String templateCode,
            @Parameter(description = "变量映射 // Variable map") 
            @RequestBody Map<String, Object> variables) {
        log.info("API调用: 渲染模板[{}] // API call: Render template [{}]", 
            templateCode, templateCode);
        String rendered = promptTemplateService.renderTemplate(templateCode, variables);
        return Result.ok(rendered);
    }

    /**
     * 获取模板使用统计 // Get template usage statistics
     */
    @Operation(summary = "获取模板统计信息 // Get template statistics")
    @GetMapping("/{templateCode}/statistics")
    public Result<Map<String, Object>> getTemplateStatistics(
            @Parameter(description = "模板编码 // Template code") 
            @PathVariable("templateCode") String templateCode) {
        log.info("API调用: 获取模板[{}]统计 // API call: Get template [{}] statistics", 
            templateCode, templateCode);
        Map<String, Object> stats = promptTemplateService.getTemplateStatistics(templateCode);
        return Result.ok(stats);
    }

    /**
     * 批量导入模板 // Batch import templates
     */
    @Operation(summary = "批量导入模板 // Batch import templates")
    @PostMapping("/batch-import")
    public Result<Integer> batchImportTemplates(@RequestBody List<PromptTemplateVo> templates) {
        log.info("API调用: 批量导入模板，数量: {} // API call: Batch import templates, count: {}", 
            templates.size(), templates.size());
        Integer count = promptTemplateService.batchImportTemplates(templates);
        return Result.ok(count);
    }

    /**
     * 克隆模板创建新版本 // Clone template to create new version
     */
    @Operation(summary = "克隆模板创建新版本 // Clone template to create new version")
    @PostMapping("/{id}/clone")
    public Result<Long> cloneTemplate(
            @Parameter(description = "原模板ID // Original template ID") 
            @PathVariable("id") Long id,
            @Parameter(description = "新版本号 // New version number") 
            @RequestParam("newVersion") String newVersion) {
        log.info("API调用: 克隆模板ID[{}]，新版本: {} // API call: Clone template ID [{}], new version: {}", 
            id, newVersion, id, newVersion);
        Long newId = promptTemplateService.cloneTemplate(id, newVersion);
        return Result.ok(newId);
    }

    /**
     * 清除所有提示词缓存 // Clear all prompt caches
     */
    @Operation(summary = "清除所有提示词缓存 // Clear all prompt caches")
    @DeleteMapping("/cache/clear")
    public Result<String> clearAllCaches() {
        log.info("API调用: 清除所有提示词缓存 // API call: Clear all prompt caches");
        
        // 清除所有AI相关缓存 // Clear all AI-related caches
        cacheManager.getCacheNames().forEach(cacheName -> {
            if (cacheName.startsWith("ai:")) {
                var cache = cacheManager.getCache(cacheName);
                if (cache != null) {
                    cache.clear();
                    log.info("缓存[{}]已清除 // Cache [{}] cleared", cacheName, cacheName);
                }
            }
        });
        
        return Result.ok("所有AI缓存已清除 // All AI caches cleared");
    }

    /**
     * 获取缓存统计信息 // Get cache statistics
     */
    @Operation(summary = "获取缓存统计信息 // Get cache statistics")
    @GetMapping("/cache/statistics")
    public Result<Map<String, Object>> getCacheStatistics() {
        log.info("API调用: 获取缓存统计信息 // API call: Get cache statistics");
        Map<String, Object> stats = cacheStatistics.getStats();
        return Result.ok(stats);
    }

    /**
     * 重置缓存统计 // Reset cache statistics
     */
    @Operation(summary = "重置缓存统计 // Reset cache statistics")
    @PostMapping("/cache/statistics/reset")
    public Result<String> resetCacheStatistics() {
        log.info("API调用: 重置缓存统计 // API call: Reset cache statistics");
        cacheStatistics.reset();
        return Result.ok("缓存统计已重置 // Cache statistics reset");
    }

    /**
     * 缓存预热 - 加载热门模板 // Cache warming - Load popular templates
     */
    @Operation(summary = "缓存预热 // Cache warming")
    @PostMapping("/cache/warm-up")
    public Result<String> warmUpCache() {
        log.info("API调用: 缓存预热 // API call: Cache warming");
        
        long startTime = System.currentTimeMillis();
        
        // 预加载所有启用的模板 // Preload all active templates
        List<PromptTemplateVo> templates = promptTemplateService.listAllTemplates();
        templates.stream()
            .filter(PromptTemplateVo::getIsActive)
            .forEach(template -> {
                // 触发缓存加载 // Trigger cache loading
                promptTemplateService.getTemplateByCode(template.getTemplateCode());
            });
        
        long duration = System.currentTimeMillis() - startTime;
        String message = String.format(
            "缓存预热完成，加载%d个模板，耗时%dms // Cache warming completed, loaded %d templates, time %dms",
            templates.size(), duration, templates.size(), duration
        );
        
        log.info(message);
        return Result.ok(message);
    }
}