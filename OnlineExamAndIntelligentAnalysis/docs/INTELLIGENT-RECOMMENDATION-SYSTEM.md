# 智能推荐系统实现文档

# Intelligent Recommendation System Implementation Guide

## 📋 系统概述 / System Overview

根据 [`AI-架构优化分析报告.md`](../AI-架构优化分析报告.md) 第 2.1 节要求，实现智能推荐系统。

### 核心功能 / Core Features

1. **学习资源推荐 / Learning Resource Recommendation**

   - 基于学生薄弱知识点推荐
   - 协同过滤算法
   - AI 个性化学习路径生成

2. **智能组卷 / Intelligent Paper Generation**
   - 基于学生能力动态生成试卷
   - 自适应难度调整
   - 知识点覆盖优化

---

## 🗄️ 数据库设计 / Database Design

### 已创建表 / Created Tables

详见: [`intelligent_recommendation.sql`](../src/main/resources/sql/intelligent_recommendation.sql)

1. **learning_resource** - 学习资源表
2. **recommendation_history** - 推荐历史表
3. **student_learning_record** - 学生学习记录表
4. **adaptive_paper_config** - 智能组卷配置表
5. **recommendation_algorithm_stats** - 算法性能统计表

---

## 🏗️ 后端架构 / Backend Architecture

### 实体类层 / Entity Layer

需创建以下实体类(均需中英文注释):

```
com.exam.entity/
├── LearningResource.java          ✅ 已创建
├── RecommendationHistory.java     ⏳ 待创建
├── StudentLearningRecord.java     ⏳ 待创建
└── AdaptivePaperConfig.java       ⏳ 待创建
```

**创建指南 / Creation Guide:**

```java
// 标准实体类模板 / Standard entity template
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "table_name", autoResultMap = true)
public class EntityName extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    // 字段定义 / Field definitions
    // 每个字段都需要中英文注释
    // Each field requires bilingual comments

    /**
     * 字段说明(中文) / Field description (English)
     */
    private String fieldName;

    // JSON字段需要使用TypeHandler
    // JSON fields need TypeHandler
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Type> jsonField;
}
```

### VO 层 / VO Layer

```
com.exam.vo/
├── LearningResourceVo.java           ⏳ 待创建
├── RecommendationRequestVo.java      ⏳ 待创建
├── RecommendationResultVo.java       ⏳ 待创建
└── AdaptivePaperRequestVo.java       ⏳ 待创建
```

**示例 VO / Example VO:**

```java
/**
 * 学习资源推荐请求VO
 * Learning Resource Recommendation Request VO
 */
@Data
public class RecommendationRequestVo {
    /**
     * 学生ID / Student ID
     */
    private Long studentId;

    /**
     * 科目ID / Subject ID
     */
    private Long subjectId;

    /**
     * 推荐数量 / Number of recommendations
     */
    private Integer limit = 5;

    /**
     * 推荐算法 / Recommendation algorithm
     * COLLABORATIVE(协同过滤), CONTENT_BASED(基于内容), AI(AI推荐)
     */
    private String algorithm = "AI";
}
```

### Mapper 层 / Mapper Layer

```
com.exam.mapper/
├── LearningResourceMapper.java           ⏳ 待创建
├── RecommendationHistoryMapper.java      ⏳ 待创建
├── StudentLearningRecordMapper.java      ⏳ 待创建
└── AdaptivePaperConfigMapper.java        ⏳ 待创建
```

**Mapper 接口模板 / Mapper Interface Template:**

```java
/**
 * 学习资源Mapper接口
 * Learning Resource Mapper Interface
 *
 * @author AI Architect Team
 */
@Mapper
public interface LearningResourceMapper extends BaseMapper<LearningResource> {

    /**
     * 根据科目和难度查询资源 / Query resources by subject and difficulty
     * @param subjectId 科目ID / Subject ID
     * @param difficultyLevel 难度等级 / Difficulty level
     * @return 资源列表 / Resource list
     */
    @Select("SELECT * FROM learning_resource WHERE subject_id = #{subjectId} AND difficulty_level = #{difficultyLevel} AND is_active = 1")
    List<LearningResource> selectBySubjectAndDifficulty(@Param("subjectId") Long subjectId,
                                                         @Param("difficultyLevel") String difficultyLevel);

    /**
     * 根据知识点ID查询资源 / Query resources by knowledge point IDs
     */
    @Select("SELECT * FROM learning_resource WHERE JSON_CONTAINS(knowledge_point_ids, CAST(#{knowledgePointId} AS JSON)) AND is_active = 1")
    List<LearningResource> selectByKnowledgePoint(@Param("knowledgePointId") Long knowledgePointId);
}
```

### Service 层 / Service Layer

#### 核心服务接口 / Core Service Interface

**IntelligentRecommendationService.java**

```java
package com.exam.service;

import com.exam.entity.LearningResource;
import com.exam.entity.Paper;
import com.exam.vo.RecommendationRequestVo;
import com.exam.vo.RecommendationResultVo;
import com.exam.vo.AdaptivePaperRequestVo;

import java.util.List;

/**
 * 智能推荐服务接口
 * Intelligent Recommendation Service Interface
 *
 * 功能说明 / Function Description:
 * - 基于学生表现推荐学习资源
 * - Recommend learning resources based on student performance
 * - 智能组卷功能
 * - Intelligent paper generation functionality
 *
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
public interface IntelligentRecommendationService {

    /**
     * 推荐学习资源 / Recommend learning resources
     *
     * 推荐流程 / Recommendation process:
     * 1. 分析学生薄弱知识点 / Analyze student weak points
     * 2. 应用协同过滤算法 / Apply collaborative filtering
     * 3. 使用AI生成个性化推荐理由 / Use AI to generate personalized reasons
     *
     * @param request 推荐请求 / Recommendation request
     * @return 推荐结果列表 / List of recommendations
     */
    List<RecommendationResultVo> recommendResources(RecommendationRequestVo request);

    /**
     * 智能组卷 / Generate adaptive paper
     *
     * 组卷策略 / Paper generation strategy:
     * 1. 评估学生当前能力 / Assess student current ability
     * 2. 计算知识点掌握度 / Calculate knowledge mastery level
     * 3. 动态选择题目 / Dynamically select questions
     * 4. 平衡难度分布 / Balance difficulty distribution
     *
     * @param request 组卷请求 / Paper generation request
     * @return 生成的试卷 / Generated paper
     */
    Paper generateAdaptivePaper(AdaptivePaperRequestVo request);

    /**
     * 获取推荐历史 / Get recommendation history
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID / Subject ID
     * @return 推荐历史列表 / Recommendation history list
     */
    List<RecommendationResultVo> getRecommendationHistory(Long studentId, Long subjectId);

    /**
     * 记录用户反馈 / Record user feedback
     * @param recommendationId 推荐记录ID / Recommendation ID
     * @param isAccepted 是否接受 / Is accepted
     * @param feedbackScore 反馈评分(1-5) / Feedback score (1-5)
     * @param comment 评论 / Comment
     */
    void recordFeedback(Long recommendationId, Boolean isAccepted, Integer feedbackScore, String comment);

    /**
     * 获取算法性能统计 / Get algorithm performance stats
     * @return 算法统计信息 / Algorithm statistics
     */
    Map<String, Object> getAlgorithmStats();
}
```

#### 服务实现类 / Service Implementation

**IntelligentRecommendationServiceImpl.java** (核心逻辑)

```java
package com.exam.service.impl;

import com.exam.ai.AIModelManager;
import com.exam.entity.*;
import com.exam.mapper.*;
import com.exam.service.*;
import com.exam.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 智能推荐服务实现类
 * Intelligent Recommendation Service Implementation
 *
 * 实现算法 / Implementation Algorithms:
 * 1. 协同过滤(Collaborative Filtering) - 基于相似学生的学习记录
 * 2. 基于内容(Content-Based) - 基于知识点相似度
 * 3. AI推荐(AI-Powered) - 使用大语言模型生成个性化推荐
 *
 * @author AI Architect Team
 * @version 1.0.0
 */
@Slf4j
@Service
public class IntelligentRecommendationServiceImpl implements IntelligentRecommendationService {

    @Resource
    private LearningResourceMapper resourceMapper;

    @Resource
    private RecommendationHistoryMapper historyMapper;

    @Resource
    private StudentLearningRecordMapper learningRecordMapper;

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private KnowledgeMasteryService knowledgeMasteryService;

    @Resource
    private ChatClient chatClient;

    @Resource
    private AIModelManager aiModelManager;

    /**
     * 推荐学习资源 / Recommend learning resources
     *
     * 实现步骤 / Implementation steps:
     * 1. 分析学生薄弱知识点
     * 2. 查找相关学习资源
     * 3. 应用推荐算法排序
     * 4. 使用AI生成推荐理由
     * 5. 记录推荐历史
     */
    @Override
    @Cacheable(value = "ai:recommendations", key = "#request.studentId + '_' + #request.subjectId", unless = "#result == null")
    public List<RecommendationResultVo> recommendResources(RecommendationRequestVo request) {
        log.info("开始推荐学习资源 / Starting resource recommendation - StudentId: {}, SubjectId: {}",
                 request.getStudentId(), request.getSubjectId());

        // 步骤1: 分析薄弱知识点 / Step 1: Analyze weak points
        List<KnowledgePointAnalysis> weakPoints = analyzeWeakKnowledgePoints(
            request.getStudentId(),
            request.getSubjectId()
        );

        if (weakPoints.isEmpty()) {
            log.info("学生无明显薄弱知识点 / No significant weak points found");
            return Collections.emptyList();
        }

        // 步骤2: 查找相关资源 / Step 2: Find related resources
        List<LearningResource> candidateResources = findResourcesByKnowledgePoints(
            weakPoints.stream().map(KnowledgePointAnalysis::getKnowledgePointId).collect(Collectors.toList())
        );

        // 步骤3: 应用推荐算法 / Step 3: Apply recommendation algorithm
        List<ScoredResource> scoredResources = switch (request.getAlgorithm()) {
            case "COLLABORATIVE" -> applyCollaborativeFiltering(request.getStudentId(), candidateResources);
            case "CONTENT_BASED" -> applyContentBasedFiltering(weakPoints, candidateResources);
            case "AI" -> applyAIRecommendation(request, weakPoints, candidateResources);
            default -> applyHybridRecommendation(request, weakPoints, candidateResources);
        };

        // 步骤4: 生成推荐结果 / Step 4: Generate recommendation results
        List<RecommendationResultVo> results = scoredResources.stream()
            .limit(request.getLimit())
            .map(sr -> buildRecommendationResult(sr, weakPoints, request.getAlgorithm()))
            .collect(Collectors.toList());

        // 步骤5: 保存推荐历史 / Step 5: Save recommendation history
        saveRecommendationHistory(request, results, weakPoints);

        log.info("推荐完成 / Recommendation completed - 推荐数量 / Count: {}", results.size());
        return results;
    }

    /**
     * 分析学生薄弱知识点 / Analyze student weak knowledge points
     *
     * 分析维度 / Analysis dimensions:
     * - 错题率 / Error rate
     * - 平均分 / Average score
     * - 答题时间 / Answer time
     * - 知识点掌握度预测 / Knowledge mastery prediction
     */
    private List<KnowledgePointAnalysis> analyzeWeakKnowledgePoints(Long studentId, Long subjectId) {
        // 实现知识点分析逻辑
        // Implementation of knowledge point analysis logic
        // ...
        return new ArrayList<>();
    }

    /**
     * AI推荐算法实现 / AI recommendation algorithm implementation
     *
     * 使用大语言模型生成个性化推荐
     * Uses LLM to generate personalized recommendations
     */
    private List<ScoredResource> applyAIRecommendation(
        RecommendationRequestVo request,
        List<KnowledgePointAnalysis> weakPoints,
        List<LearningResource> resources
    ) {
        // 构建AI提示词 / Build AI prompt
        String prompt = buildRecommendationPrompt(request.getStudentId(), weakPoints, resources);

        // 调用AI模型 / Call AI model
        String aiResponse = chatClient.prompt()
            .user(prompt)
            .call()
            .content();

        // 解析AI响应并评分 / Parse AI response and score
        return parseAIRecommendation(aiResponse, resources);
    }

    /**
     * 构建推荐提示词 / Build recommendation prompt
     */
    private String buildRecommendationPrompt(
        Long studentId,
        List<KnowledgePointAnalysis> weakPoints,
        List<LearningResource> resources
    ) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("作为一个教育专家，请为学生推荐最合适的学习资源。\n");
        prompt.append("As an education expert, please recommend the most suitable learning resources.\n\n");

        prompt.append("学生薄弱知识点 / Student weak points:\n");
        for (KnowledgePointAnalysis wp : weakPoints) {
            prompt.append(String.format("- %s (掌握度 / Mastery: %.1f%%)\n",
                wp.getKnowledgePointName(), wp.getMasteryLevel() * 100));
        }

        prompt.append("\n可选资源 / Available resources:\n");
        for (int i = 0; i < resources.size(); i++) {
            LearningResource r = resources.get(i);
            prompt.append(String.format("%d. %s (%s, 难度 / Difficulty: %s, 时长 / Duration: %d分钟)\n",
                i + 1, r.getResourceName(), r.getResourceType(), r.getDifficultyLevel(), r.getDurationMinutes()));
        }

```
