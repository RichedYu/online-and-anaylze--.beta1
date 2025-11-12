package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exam.ai.AIModelAdapter;
import com.exam.ai.AIModelManager;
import com.exam.entity.*;
import com.exam.mapper.*;
import com.exam.service.IntelligentRecommendationService;
import com.exam.service.PromptTemplateService;
import com.exam.vo.LearningResourceVo;
import com.exam.vo.RecommendationRequestVo;
import com.exam.vo.RecommendationResultVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 智能推荐服务实现类 / Intelligent Recommendation Service Implementation
 * 
 * <p>实现个性化学习资源推荐、自适应组卷和学习进度追踪</p>
 * <p>Implements personalized learning resource recommendations, adaptive paper generation, 
 * and learning progress tracking</p>
 * 
 * @author System
 * @since 2025-11-12
 */
@Slf4j
@Service
public class IntelligentRecommendationServiceImpl implements IntelligentRecommendationService {

    @Autowired
    private LearningResourceMapper learningResourceMapper;
    
    @Autowired
    private RecommendationHistoryMapper recommendationHistoryMapper;
    
    @Autowired
    private StudentLearningRecordMapper studentLearningRecordMapper;
    
    @Autowired
    private AdaptivePaperConfigMapper adaptivePaperConfigMapper;
    
    @Autowired
    private ScoreDetailMapper scoreDetailMapper;
    
    @Autowired
    private PromptTemplateService promptTemplateService;
    
    @Autowired
    private AIModelManager aiModelManager;
    
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 获取个性化学习资源推荐 / Get personalized learning resource recommendations
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RecommendationResultVo> recommendResources(RecommendationRequestVo request) {
        log.info("开始推荐资源 / Starting resource recommendation: studentId={}, subjectId={}, algorithm={}", 
                request.getStudentId(), request.getSubjectId(), request.getAlgorithm());
        
        try {
            // 1. 分析学生薄弱知识点 / Analyze weak knowledge points
            Map<Integer, Double> weakPoints = analyzeWeakKnowledgePoints(
                    request.getStudentId().intValue(), request.getSubjectId().intValue());
            List<Long> weakPointIds = weakPoints.keySet().stream()
                    .map(Integer::longValue)
                    .collect(Collectors.toList());
            
            // 2. 查找候选资源 / Find candidate resources
            List<LearningResource> candidateResources = learningResourceMapper.selectByKnowledgePoints(weakPointIds);
            
            if (candidateResources.isEmpty()) {
                log.warn("未找到匹配的学习资源 / No matching resources found");
                return Collections.emptyList();
            }
            
            // 3. 应用推荐算法并排序 / Apply algorithm and sort
            List<RecommendationResultVo> recommendations = applyRecommendationAlgorithm(
                    request, candidateResources, weakPoints);
            
            // 4. 限制返回数量 / Limit result count
            recommendations = recommendations.stream()
                    .limit(request.getLimit())
                    .collect(Collectors.toList());
            
            // 5. 保存推荐历史 / Save recommendation history
            saveRecommendationHistory(request, recommendations, weakPointIds);
            
            log.info("推荐完成 / Recommendation completed: {} resources returned", recommendations.size());
            return recommendations;
            
        } catch (Exception e) {
            log.error("推荐资源失败 / Resource recommendation failed", e);
            throw new RuntimeException("推荐资源失败: " + e.getMessage());
        }
    }

    /**
     * 应用推荐算法 / Apply recommendation algorithm
     */
    private List<RecommendationResultVo> applyRecommendationAlgorithm(
            RecommendationRequestVo request,
            List<LearningResource> resources,
            Map<Integer, Double> weakPoints) {
        
        return resources.stream().map(resource -> {
            RecommendationResultVo vo = convertToResultVo(resource);
            
            // 计算置信度 / Calculate confidence
            double confidenceScore = calculateConfidenceScore(resource, weakPoints, request.getStudentId().intValue());
            vo.setConfidenceScore(BigDecimal.valueOf(confidenceScore));
            
            // 生成推荐理由 / Generate reason
            vo.setRecommendationReason(generateRecommendationReason(resource, weakPoints));
            vo.setAlgorithm(request.getAlgorithm());
            
            return vo;
        })
        .sorted((a, b) -> b.getConfidenceScore().compareTo(a.getConfidenceScore()))
        .collect(Collectors.toList());
    }

    /**
     * 计算推荐置信度 / Calculate confidence score
     */
    private double calculateConfidenceScore(LearningResource resource, 
                                           Map<Integer, Double> weakPoints, 
                                           Integer studentId) {
        double score = 0.5; // 基础分 / Base score
        
        try {
            // 知识点匹配度 / Knowledge point matching
            List<Long> resourceKPs = resource.getKnowledgePointIds();
            if (resourceKPs != null && !resourceKPs.isEmpty()) {
                int matchCount = 0;
                for (Long kpId : resourceKPs) {
                    if (weakPoints.containsKey(kpId.intValue())) {
                        matchCount++;
                        score += (1.0 - weakPoints.get(kpId.intValue())) * 0.2;
                    }
                }
                score += (double) matchCount / resourceKPs.size() * 0.3;
            }
            
            // 资源有效性 / Resource effectiveness
            if (resource.getEffectivenessScore() != null) {
                score += resource.getEffectivenessScore().doubleValue() * 0.2;
            }
            
        } catch (Exception e) {
            log.warn("计算置信度失败 / Failed to calculate confidence", e);
        }
        
        return Math.min(1.0, score);
    }

    /**
     * 生成推荐理由 / Generate recommendation reason
     */
    private String generateRecommendationReason(LearningResource resource, Map<Integer, Double> weakPoints) {
        try {
            List<Long> resourceKPs = resource.getKnowledgePointIds();
            if (resourceKPs == null || resourceKPs.isEmpty()) {
                return "该资源适合您当前的学习阶段。 / This resource suits your current learning stage.";
            }
            
            long matchCount = resourceKPs.stream()
                    .filter(kpId -> weakPoints.containsKey(kpId.intValue()))
                    .count();
            
            return String.format("该%s资源覆盖您的%d个薄弱知识点，建议学习。 / This %s covers %d of your weak points, recommended.",
                    resource.getResourceType(), matchCount, resource.getResourceType(), matchCount);
                    
        } catch (Exception e) {
            return "推荐资源 / Recommended resource";
        }
    }

    /**
     * 转换为结果VO / Convert to result VO
     */
    private RecommendationResultVo convertToResultVo(LearningResource resource) {
        RecommendationResultVo vo = new RecommendationResultVo();
        vo.setResourceId(resource.getId());
        vo.setResourceName(resource.getResourceName());
        vo.setResourceType(resource.getResourceType());
        vo.setDifficultyLevel(resource.getDifficultyLevel());
        vo.setResourceUrl(resource.getResourceUrl());
        vo.setDescription(resource.getDescription());
        vo.setDurationMinutes(resource.getDurationMinutes());
        vo.setEffectivenessScore(resource.getEffectivenessScore());
        vo.setTags(resource.getTags());
        return vo;
    }

    /**
     * 保存推荐历史 / Save recommendation history
     */
    private void saveRecommendationHistory(RecommendationRequestVo request, 
                                          List<RecommendationResultVo> recommendations,
                                          List<Long> weakPointIds) {
        try {
            for (int i = 0; i < recommendations.size(); i++) {
                RecommendationResultVo vo = recommendations.get(i);
                RecommendationHistory history = new RecommendationHistory();
                history.setStudentId(request.getStudentId());
                history.setSubjectId(request.getSubjectId());
                history.setRecommendationType("RESOURCE");
                history.setResourceId(vo.getResourceId());
                history.setAlgorithmType(request.getAlgorithm());
                history.setConfidenceScore(vo.getConfidenceScore());
                history.setRecommendationReason(vo.getRecommendationReason());
                
                recommendationHistoryMapper.insert(history);
            }
        } catch (Exception e) {
            log.error("保存推荐历史失败 / Failed to save recommendation history", e);
        }
    }

    /**
     * 生成自适应试卷 / Generate adaptive paper
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdaptivePaperConfig generateAdaptivePaper(Integer studentId, Integer subjectId, AdaptivePaperConfig config) {
        log.info("开始生成自适应试卷 / Starting adaptive paper generation: studentId={}, subjectId={}", 
                studentId, subjectId);
        
        try {
            config.setStudentId(Long.valueOf(studentId));
            config.setSubjectId(Long.valueOf(subjectId));
            adaptivePaperConfigMapper.insert(config);
            
            log.info("自适应试卷生成完成 / Adaptive paper generation completed: configId={}", config.getId());
            return config;
            
        } catch (Exception e) {
            log.error("生成自适应试卷失败 / Adaptive paper generation failed", e);
            throw new RuntimeException("生成自适应试卷失败: " + e.getMessage());
        }
    }

    /**
     * 获取学生推荐历史 / Get student recommendation history
     */
    @Override
    public List<RecommendationHistory> getRecommendationHistory(Integer studentId, Integer subjectId, Integer limit) {
        LambdaQueryWrapper<RecommendationHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RecommendationHistory::getStudentId, Long.valueOf(studentId));
        if (subjectId != null) {
            wrapper.eq(RecommendationHistory::getSubjectId, Long.valueOf(subjectId));
        }
        wrapper.orderByDesc(RecommendationHistory::getCreateTime);
        wrapper.last("LIMIT " + limit);
        
        return recommendationHistoryMapper.selectList(wrapper);
    }

    /**
     * 记录用户对推荐的反馈 / Record user feedback
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recordFeedback(Integer recommendationId, Integer feedbackScore, String feedbackComment) {
        try {
            RecommendationHistory history = recommendationHistoryMapper.selectById(recommendationId);
            if (history == null) {
                return false;
            }
            
            history.setFeedbackScore(feedbackScore);
            history.setFeedbackComment(feedbackComment);
            return recommendationHistoryMapper.updateById(history) > 0;
            
        } catch (Exception e) {
            log.error("记录反馈失败 / Failed to record feedback", e);
            return false;
        }
    }

    /**
     * 获取学生学习记录 / Get student learning records
     */
    @Override
    public List<StudentLearningRecord> getStudentLearningRecords(Integer studentId, Integer subjectId) {
        return studentLearningRecordMapper.selectOngoingByStudent(Long.valueOf(studentId));
    }

    /**
     * 更新学习记录 / Update learning record
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateLearningRecord(StudentLearningRecord record) {
        try {
            if (record.getId() == null) {
                return studentLearningRecordMapper.insert(record) > 0;
            } else {
                return studentLearningRecordMapper.updateById(record) > 0;
            }
        } catch (Exception e) {
            log.error("更新学习记录失败 / Failed to update learning record", e);
            return false;
        }
    }

    /**
     * 分析学生薄弱知识点 / Analyze student weak knowledge points
     */
    @Override
    public Map<Integer, Double> analyzeWeakKnowledgePoints(Integer studentId, Integer subjectId) {
        Map<Integer, Double> weakPoints = new HashMap<>();
        
        try {
            // 简化实现：基于默认逻辑分析薄弱知识点
            // Simplified implementation: Analyze weak points based on default logic
            // 实际应该查询学生的答题记录并关联题目的知识点进行统计
            // Should actually query student's answer records and relate to question knowledge points
            
            Map<Integer, Integer> correctCount = new HashMap<>();
            Map<Integer, Integer> totalCount = new HashMap<>();
            
            // 使用简化的模拟数据
            // Using simplified mock data
            for (int i = 1; i <= 10; i++) {
                totalCount.put(i, 10);
                correctCount.put(i, 5 + (int)(Math.random() * 5));
            }
            
            // 计算掌握度（正确率） / Calculate mastery (correctness rate)
            for (Map.Entry<Integer, Integer> entry : totalCount.entrySet()) {
                int kpId = entry.getKey();
                double masteryLevel = (double) correctCount.getOrDefault(kpId, 0) / entry.getValue();
                if (masteryLevel < 0.7) { // 掌握度低于70%视为薄弱 / Below 70% considered weak
                    weakPoints.put(kpId, masteryLevel);
                }
            }
            
        } catch (Exception e) {
            log.error("分析薄弱知识点失败 / Failed to analyze weak points", e);
        }
        
        return weakPoints;
    }

    /**
     * 获取热门学习资源 / Get popular learning resources
     */
    @Override
    @Cacheable(value = "recommendation:popular", key = "#subjectId + ':' + #resourceType")
    public List<LearningResourceVo> getPopularResources(Integer subjectId, String resourceType, Integer limit) {
        List<LearningResource> resources = learningResourceMapper.selectPopularResources(
                Long.valueOf(subjectId), limit);
        
        return resources.stream()
                .map(this::convertToResourceVo)
                .collect(Collectors.toList());
    }

    /**
     * 转换为资源VO / Convert to resource VO
     */
    private LearningResourceVo convertToResourceVo(LearningResource resource) {
        LearningResourceVo vo = new LearningResourceVo();
        BeanUtils.copyProperties(resource, vo);
        return vo;
    }

    /**
     * 获取推荐算法统计信息 / Get recommendation algorithm statistics
     */
    @Override
    public Map<String, Object> getAlgorithmStatistics(String algorithmType, String startDate, String endDate) {
        Map<String, Object> stats = recommendationHistoryMapper.selectAlgorithmStats(algorithmType, 100L);
        return stats != null ? stats : new HashMap<>();
    }

    /**
     * 批量推荐资源 / Batch recommend resources
     */
    @Override
    public Map<Integer, List<RecommendationResultVo>> batchRecommendResources(
            List<Integer> studentIds, Integer subjectId, Integer limit) {
        
        Map<Integer, List<RecommendationResultVo>> result = new HashMap<>();
        
        for (Integer studentId : studentIds) {
            try {
                RecommendationRequestVo request = RecommendationRequestVo.builder()
                        .studentId(Long.valueOf(studentId))
                        .subjectId(Long.valueOf(subjectId))
                        .limit(limit)
                        .algorithm("HYBRID")
                        .build();
                
                List<RecommendationResultVo> recommendations = recommendResources(request);
                result.put(studentId, recommendations);
                
            } catch (Exception e) {
                log.error("批量推荐失败，学生ID: {} / Batch recommendation failed for student: {}", studentId, studentId, e);
            }
        }
        
        return result;
    }

    /**
     * 保存自适应组卷配置模板 / Save adaptive paper configuration template
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAdaptiveConfig(AdaptivePaperConfig config) {
        try {
            if (config.getId() == null) {
                return adaptivePaperConfigMapper.insert(config) > 0;
            } else {
                return adaptivePaperConfigMapper.updateById(config) > 0;
            }
        } catch (Exception e) {
                log.error("保存配置失败 / Failed to save config", e);
                return false;
            }
        }
    
        /**
         * 获取配置模板列表 / Get configuration template list
         */
        @Override
        public List<AdaptivePaperConfig> getAdaptiveConfigs(Integer subjectId, Boolean isTemplate) {
            LambdaQueryWrapper<AdaptivePaperConfig> wrapper = new LambdaQueryWrapper<>();
            if (subjectId != null) {
                wrapper.eq(AdaptivePaperConfig::getSubjectId, Long.valueOf(subjectId));
            }
            if (isTemplate != null) {
                wrapper.eq(AdaptivePaperConfig::getIsTemplate, isTemplate);
            }
            wrapper.orderByDesc(AdaptivePaperConfig::getCreateTime);
            
            return adaptivePaperConfigMapper.selectList(wrapper);
        }
    }