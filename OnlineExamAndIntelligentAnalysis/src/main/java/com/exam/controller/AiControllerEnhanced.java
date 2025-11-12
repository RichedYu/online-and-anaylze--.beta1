package com.exam.controller;

import com.exam.ai.AIModelManager;
import com.exam.ai.AIModelRequest;
import com.exam.config.AiCacheConfig;
import com.exam.service.AnalysisService;
import com.exam.service.PromptTemplateService;
import com.exam.utils.result.Result;
import com.exam.vo.KnowledgePointsAnalysisVo;
import com.exam.vo.LeiDaTuVo;
import com.exam.vo.QuestionTypeAnalysisVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增强版AI控制器 // Enhanced AI Controller
 * 集成提示词管理系统和缓存策略 // Integrated with prompt template management and cache strategy
 * 
 * 主要改进: // Main improvements:
 * 1. 使用提示词模板系统替代硬编码 // Use template system instead of hardcoded prompts
 * 2. 集成多级缓存策略 // Integrated multi-level cache strategy
 * 3. 添加缓存统计和监控 // Added cache statistics and monitoring
 * 
 * @author System
 * @date 2025-11-12
 */
@Slf4j
@Tag(name = "AI智能分析模块(增强版) // AI Intelligent Analysis Module (Enhanced)")
@RestController
@RequestMapping("/login/ai-enhanced")
public class AiControllerEnhanced {

    @Resource
    private AIModelManager aiModelManager;
    
    @Resource
    private AnalysisService analysisService;
    
    @Resource
    private PromptTemplateService promptTemplateService;
    
    @Resource
    private AiCacheConfig.CacheStatistics cacheStatistics;

    /**
     * 考试分析1 - 题型分析(使用提示词模板) // Exam Analysis 1 - Question Type Analysis (using template)
     */
    @Operation(summary = "考试分析1 - 题型分析(模板化) // Exam Analysis 1 - Question Type (Templated)")
    @PostMapping(value = "/examAnalysis1")
    @Cacheable(value = "ai:analysis", key = "'exam1:' + #questionTypeAnalysisVoList.hashCode() + ':' + #modelCode", 
               cacheManager = "aiCacheManager")
    public Result<HashMap<String, Object>> examAnalysis1(
            @RequestBody List<QuestionTypeAnalysisVo> questionTypeAnalysisVoList,
            @RequestParam(value = "model", required = false) String modelCode) {
        
        log.info("增强版API: 考试分析1(题型) // Enhanced API: Exam Analysis 1 (Question Type)");
        long startTime = System.currentTimeMillis();
        
        try {
            // 使用提示词模板系统 // Use prompt template system
            Map<String, Object> variables = new HashMap<>();
            variables.put("data", questionTypeAnalysisVoList.toString());
            variables.put("wordCount", "250");
            
            // 渲染模板 // Render template
            String userInput = promptTemplateService.renderTemplate("EXAM_ANALYSIS_TYPE", variables);
            
            // 调用AI模型 // Call AI model
            String output = aiModelManager.resolveAdapter(modelCode)
                    .chat(userInput, AIModelRequest.simple());
            
            // 记录缓存未命中 // Record cache miss
            long responseTime = System.currentTimeMillis() - startTime;
            cacheStatistics.recordMiss(responseTime);
            
            HashMap<String, Object> result = new HashMap<>();
            result.put("message", output);
            result.put("cached", false);
            result.put("responseTime", responseTime + "ms");
            result.put("templateUsed", "EXAM_ANALYSIS_TYPE");
            
            log.info("考试分析1完成，耗时: {}ms // Exam Analysis 1 completed, time: {}ms", 
                responseTime, responseTime);
            
            return Result.ok(result);
            
        } catch (Exception e) {
            log.error("考试分析1失败 // Exam Analysis 1 failed", e);
            throw e;
        }
    }

    /**
     * 考试分析2 - 难度分析(使用提示词模板) // Exam Analysis 2 - Difficulty Analysis (using template)
     */
    @Operation(summary = "考试分析2 - 难度分析(模板化) // Exam Analysis 2 - Difficulty (Templated)")
    @PostMapping(value = "/examAnalysis2")
    @Cacheable(value = "ai:analysis", key = "'exam2:' + #questionTypeAnalysisVoList.hashCode() + ':' + #modelCode",
               cacheManager = "aiCacheManager")
    public Result<HashMap<String, Object>> examAnalysis2(
            @RequestBody List<QuestionTypeAnalysisVo> questionTypeAnalysisVoList,
            @RequestParam(value = "model", required = false) String modelCode) {
        
        log.info("增强版API: 考试分析2(难度) // Enhanced API: Exam Analysis 2 (Difficulty)");
        long startTime = System.currentTimeMillis();
        
        try {
            // 使用提示词模板系统 // Use prompt template system
            Map<String, Object> variables = new HashMap<>();
            variables.put("data", questionTypeAnalysisVoList.toString());
            variables.put("wordCount", "250");
            
            String userInput = promptTemplateService.renderTemplate("EXAM_ANALYSIS_DIFFICULTY", variables);
            
            String output = aiModelManager.resolveAdapter(modelCode)
                    .chat(userInput, AIModelRequest.simple());
            
            long responseTime = System.currentTimeMillis() - startTime;
            cacheStatistics.recordMiss(responseTime);
            
            HashMap<String, Object> result = new HashMap<>();
            result.put("message", output);
            result.put("cached", false);
            result.put("responseTime", responseTime + "ms");
            result.put("templateUsed", "EXAM_ANALYSIS_DIFFICULTY");
            
            log.info("考试分析2完成，耗时: {}ms // Exam Analysis 2 completed, time: {}ms", 
                responseTime, responseTime);
            
            return Result.ok(result);
            
        } catch (Exception e) {
            log.error("考试分析2失败 // Exam Analysis 2 failed", e);
            throw e;
        }
    }

    /**
     * 考试分析3 - 知识点掌握分析(使用提示词模板) // Exam Analysis 3 - Knowledge Points (using template)
     */
    @Operation(summary = "考试分析3 - 知识点分析(模板化) // Exam Analysis 3 - Knowledge Points (Templated)")
    @PostMapping(value = "/examAnalysis3")
    @Cacheable(value = "ai:analysis", key = "'exam3:' + #knowledgePointsAnalysisVos.hashCode() + ':' + #modelCode",
               cacheManager = "aiCacheManager")
    public Result<HashMap<String, Object>> examAnalysis3(
            @RequestBody List<KnowledgePointsAnalysisVo> knowledgePointsAnalysisVos,
            @RequestParam(value = "model", required = false) String modelCode) {
        
        log.info("增强版API: 考试分析3(知识点) // Enhanced API: Exam Analysis 3 (Knowledge Points)");
        long startTime = System.currentTimeMillis();
        
        try {
            // 使用提示词模板系统 // Use prompt template system
            Map<String, Object> variables = new HashMap<>();
            variables.put("data", knowledgePointsAnalysisVos.toString());
            variables.put("wordCount", "400");
            
            String userInput = promptTemplateService.renderTemplate("EXAM_ANALYSIS_KNOWLEDGE", variables);
            
            String output = aiModelManager.resolveAdapter(modelCode)
                    .chat(userInput, AIModelRequest.simple());
            
            long responseTime = System.currentTimeMillis() - startTime;
            cacheStatistics.recordMiss(responseTime);
            
            HashMap<String, Object> result = new HashMap<>();
            result.put("message", output);
            result.put("cached", false);
            result.put("responseTime", responseTime + "ms");
            result.put("templateUsed", "EXAM_ANALYSIS_KNOWLEDGE");
            
            log.info("考试分析3完成，耗时: {}ms // Exam Analysis 3 completed, time: {}ms", 
                responseTime, responseTime);
            
            return Result.ok(result);
            
        } catch (Exception e) {
            log.error("考试分析3失败 // Exam Analysis 3 failed", e);
            throw e;
        }
    }

    /**
     * 考试分析4 - 章节得分率分析(使用提示词模板) // Exam Analysis 4 - Chapter Score Rate (using template)
     */
    @Operation(summary = "考试分析4 - 章节分析(模板化) // Exam Analysis 4 - Chapter (Templated)")
    @GetMapping(value = "/examAnalysis4/{scoreId}")
    @Cacheable(value = "ai:analysis", key = "'exam4:' + #scoreId + ':' + #modelCode",
               cacheManager = "aiCacheManager")
    public Result<HashMap<String, Object>> examAnalysis4(
            @PathVariable("scoreId") Long scoreId,
            @RequestParam(value = "model", required = false) String modelCode) {
        
        log.info("增强版API: 考试分析4(章节)，scoreId: {} // Enhanced API: Exam Analysis 4 (Chapter), scoreId: {}", 
            scoreId, scoreId);
        long startTime = System.currentTimeMillis();
        
        try {
            // 获取数据 // Get data
            LeiDaTuVo scoreRate = analysisService.getExamQuestionChapterScoreRate(scoreId);
            List<HashMap<String, Object>> chapterData = analysisService.getExamQuestionChapter(scoreId);
            
            // 使用提示词模板系统 // Use prompt template system
            Map<String, Object> variables = new HashMap<>();
            variables.put("chapterData", chapterData.toString());
            variables.put("scoreRate", scoreRate.toString());
            variables.put("wordCount", "250");
            
            String userInput = promptTemplateService.renderTemplate("EXAM_ANALYSIS_CHAPTER", variables);
            
            String output = aiModelManager.resolveAdapter(modelCode)
                    .chat(userInput, AIModelRequest.simple());
            
            long responseTime = System.currentTimeMillis() - startTime;
            cacheStatistics.recordMiss(responseTime);
            
            HashMap<String, Object> result = new HashMap<>();
            result.put("message", output);
            result.put("cached", false);
            result.put("responseTime", responseTime + "ms");
            result.put("templateUsed", "EXAM_ANALYSIS_CHAPTER");
            
            log.info("考试分析4完成，耗时: {}ms // Exam Analysis 4 completed, time: {}ms", 
                responseTime, responseTime);
            
            return Result.ok(result);
            
        } catch (Exception e) {
            log.error("考试分析4失败 // Exam Analysis 4 failed", e);
            throw e;
        }
    }

    /**
     * 通用AI对话(使用提示词模板) // General AI chat (using template)
     */
    @Operation(summary = "通用AI对话(支持模板) // General AI chat (supports template)")
    @GetMapping(value = "/chat", produces = "text/html;charset=UTF-8")
    @Cacheable(value = "ai:chat", key = "'chat:' + #userInput.hashCode() + ':' + #modelCode + ':' + #templateCode",
               cacheManager = "aiCacheManager", unless = "#result == null")
    public String chat(
            @RequestParam("userInput") String userInput,
            @RequestParam(value = "model", required = false) String modelCode,
            @RequestParam(value = "template", required = false) String templateCode,
            @RequestParam(value = "variables", required = false) Map<String, Object> variables) {
        
        log.info("增强版API: AI对话 // Enhanced API: AI chat");
        long startTime = System.currentTimeMillis();
        
        try {
            String finalPrompt = userInput;
            
            // 如果指定了模板，则使用模板渲染 // Use template rendering if specified
            if (templateCode != null && !templateCode.isEmpty()) {
                finalPrompt = promptTemplateService.renderTemplate(templateCode, variables);
                log.info("使用模板: {} // Using template: {}", templateCode, templateCode);
            }
            
            String output = aiModelManager.resolveAdapter(modelCode)
                    .chat(finalPrompt, AIModelRequest.withFunction("examAnalysisFunction"));
            
            long responseTime = System.currentTimeMillis() - startTime;
            cacheStatistics.recordMiss(responseTime);
            
            log.info("AI对话完成，耗时: {}ms // AI chat completed, time: {}ms", 
                responseTime, responseTime);
            
            return output;
            
        } catch (Exception e) {
            log.error("AI对话失败 // AI chat failed", e);
            throw e;
        }
    }

    /**
     * 流式对话(增强版) // Streaming chat (enhanced)
     */
    @Operation(summary = "流式对话(增强版) // Streaming chat (enhanced)")
    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(
            @RequestParam(value = "model", required = false) String modelCode,
            @RequestParam(value = "userInput", defaultValue = "请用两百字介绍自己") String userInput) {
        
        log.info("增强版API: 流式对话 // Enhanced API: Streaming chat");
        
        return aiModelManager.resolveAdapter(modelCode)
                .stream(userInput, AIModelRequest.withFunction("examAnalysisFunction"));
    }
}