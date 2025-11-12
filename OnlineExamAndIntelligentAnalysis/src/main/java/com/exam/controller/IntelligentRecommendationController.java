package com.exam.controller;

import com.exam.entity.AdaptivePaperConfig;
import com.exam.entity.RecommendationHistory;
import com.exam.entity.StudentLearningRecord;
import com.exam.service.IntelligentRecommendationService;
import com.exam.utils.result.Result;
import com.exam.vo.LearningResourceVo;
import com.exam.vo.RecommendationRequestVo;
import com.exam.vo.RecommendationResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 智能推荐控制器 / Intelligent Recommendation Controller
 * 
 * <p>提供个性化学习资源推荐、自适应组卷和学习进度管理的RESTful API</p>
 * <p>Provides RESTful APIs for personalized learning resource recommendations, 
 * adaptive paper generation, and learning progress management</p>
 * 
 * <p><b>主要功能 / Main Features:</b></p>
 * <ul>
 *   <li>学习资源推荐 / Learning resource recommendation</li>
 *   <li>自适应试卷生成 / Adaptive paper generation</li>
 *   <li>推荐历史查询 / Recommendation history query</li>
 *   <li>学习进度追踪 / Learning progress tracking</li>
 *   <li>用户反馈收集 / User feedback collection</li>
 * </ul>
 * 
 * @author System
 * @since 2025-11-12
 */
@Slf4j
@RestController
@RequestMapping("/login/recommendation")
@Tag(name = "智能推荐系统 / Intelligent Recommendation System", 
     description = "个性化学习资源推荐和自适应组卷 / Personalized recommendations and adaptive paper generation")
public class IntelligentRecommendationController {

    @Autowired
    private IntelligentRecommendationService recommendationService;

    /**
     * 获取个性化学习资源推荐 / Get personalized learning resource recommendations
     * 
     * <p>根据学生的学习情况和薄弱知识点，智能推荐最适合的学习资源</p>
     * <p>Intelligently recommend the most suitable learning resources based on student's 
     * learning status and weak knowledge points</p>
     * 
     * @param request 推荐请求参数 / Recommendation request parameters
     * @return 推荐结果列表 / List of recommendation results
     */
    @PostMapping("/resources")
    @Operation(summary = "获取学习资源推荐 / Get resource recommendations",
               description = "基于学生学习数据和AI算法推荐个性化学习资源 / Recommend personalized resources based on student data and AI algorithms")
    public Result<List<RecommendationResultVo>> getRecommendations(
            @RequestBody @Parameter(description = "推荐请求参数 / Request parameters") RecommendationRequestVo request) {
        
        log.info("收到推荐请求 / Received recommendation request: studentId={}, subjectId={}, algorithm={}", 
                request.getStudentId(), request.getSubjectId(), request.getAlgorithm());
        
        try {
            List<RecommendationResultVo> recommendations = recommendationService.recommendResources(request);
            
            log.info("推荐成功 / Recommendation successful: {} resources returned", recommendations.size());
            return Result.ok(recommendations);
            
        } catch (Exception e) {
            log.error("推荐失败 / Recommendation failed", e);
            return Result.fail(201, "推荐失败: " + e.getMessage());
        }
    }

    /**
     * 生成自适应试卷 / Generate adaptive paper
     * 
     * <p>根据学生的薄弱知识点和目标难度，智能生成个性化试卷配置</p>
     * <p>Intelligently generate personalized paper configuration based on weak points 
     * and target difficulty</p>
     * 
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID / Subject ID
     * @param config 组卷配置 / Paper configuration
     * @return 生成的试卷配置 / Generated paper configuration
     */
    @PostMapping("/adaptive-paper")
    @Operation(summary = "生成自适应试卷 / Generate adaptive paper",
               description = "基于学生薄弱点智能生成个性化试卷 / Generate personalized paper based on student weak points")
    public Result<AdaptivePaperConfig> generateAdaptivePaper(
            @RequestParam @Parameter(description = "学生ID / Student ID") Integer studentId,
            @RequestParam @Parameter(description = "科目ID / Subject ID") Integer subjectId,
            @RequestBody @Parameter(description = "组卷配置 / Configuration") AdaptivePaperConfig config) {
        
        log.info("生成自适应试卷 / Generating adaptive paper: studentId={}, subjectId={}", studentId, subjectId);
        
        try {
            AdaptivePaperConfig result = recommendationService.generateAdaptivePaper(studentId, subjectId, config);
            
            log.info("试卷生成成功 / Paper generation successful: configId={}", result.getId());
            return Result.ok(result);
            
        } catch (Exception e) {
            log.error("试卷生成失败 / Paper generation failed", e);
            return Result.fail(201, "试卷生成失败: " + e.getMessage());
        }
    }

    /**
     * 获取学生推荐历史 / Get student recommendation history
     * 
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID（可选） / Subject ID (optional)
     * @param limit 返回数量，默认10 / Return count, default 10
     * @return 推荐历史列表 / List of recommendation history
     */
    @GetMapping("/history/{studentId}")
    @Operation(summary = "查询推荐历史 / Query recommendation history",
               description = "查询学生的历史推荐记录 / Query student's historical recommendation records")
    public Result<List<RecommendationHistory>> getRecommendationHistory(
            @PathVariable @Parameter(description = "学生ID / Student ID") Integer studentId,
            @RequestParam(required = false) @Parameter(description = "科目ID / Subject ID") Integer subjectId,
            @RequestParam(defaultValue = "10") @Parameter(description = "返回数量 / Limit") Integer limit) {
        
        log.info("查询推荐历史 / Querying recommendation history: studentId={}, subjectId={}", studentId, subjectId);
        
        try {
            List<RecommendationHistory> history = recommendationService.getRecommendationHistory(
                    studentId, subjectId, limit);
            
            return Result.ok(history);
            
        } catch (Exception e) {
            log.error("查询推荐历史失败 / Failed to query history", e);
            return Result.fail(201, "查询失败: " + e.getMessage());
        }
    }

    /**
     * 提交推荐反馈 / Submit recommendation feedback
     * 
     * <p>允许学生对推荐结果进行评分和评论，用于优化推荐算法</p>
     * <p>Allow students to rate and comment on recommendations to optimize algorithms</p>
     * 
     * @param recommendationId 推荐ID / Recommendation ID
     * @param feedbackScore 反馈评分(1-5分) / Feedback score (1-5)
     * @param feedbackComment 反馈评论 / Feedback comment
     * @return 是否提交成功 / Whether submission was successful
     */
    @PostMapping("/feedback")
    @Operation(summary = "提交推荐反馈 / Submit feedback",
               description = "学生对推荐结果进行评分和评论 / Students rate and comment on recommendations")
    public Result<Boolean> submitFeedback(
            @RequestParam @Parameter(description = "推荐ID / Recommendation ID") Integer recommendationId,
            @RequestParam @Parameter(description = "反馈评分(1-5) / Score (1-5)") Integer feedbackScore,
            @RequestParam(required = false) @Parameter(description = "反馈评论 / Comment") String feedbackComment) {
        
        log.info("提交推荐反馈 / Submitting feedback: recommendationId={}, score={}", 
                recommendationId, feedbackScore);
        
        try {
            if (feedbackScore < 1 || feedbackScore > 5) {
                return Result.fail(202, "反馈评分必须在1-5之间 / Feedback score must be between 1-5");
            }
            
            boolean success = recommendationService.recordFeedback(
                    recommendationId, feedbackScore, feedbackComment);
            
            if (success) {
                log.info("反馈提交成功 / Feedback submitted successfully");
                return Result.ok(true);
            } else {
                return Result.fail(201, "反馈提交失败 / Failed to submit feedback");
            }
            
        } catch (Exception e) {
            log.error("反馈提交失败 / Feedback submission failed", e);
            return Result.fail(201, "反馈提交失败: " + e.getMessage());
        }
    }

    /**
     * 获取学生学习记录 / Get student learning records
     * 
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID（可选） / Subject ID (optional)
     * @return 学习记录列表 / List of learning records
     */
    @GetMapping("/learning-records/{studentId}")
    @Operation(summary = "查询学习记录 / Query learning records",
               description = "查询学生的学习进度和完成情况 / Query student's learning progress and completion")
    public Result<List<StudentLearningRecord>> getLearningRecords(
            @PathVariable @Parameter(description = "学生ID / Student ID") Integer studentId,
            @RequestParam(required = false) @Parameter(description = "科目ID / Subject ID") Integer subjectId) {
        
        log.info("查询学习记录 / Querying learning records: studentId={}, subjectId={}", studentId, subjectId);
        
        try {
            List<StudentLearningRecord> records = recommendationService.getStudentLearningRecords(
                    studentId, subjectId);
            
            return Result.ok(records);
            
        } catch (Exception e) {
            log.error("查询学习记录失败 / Failed to query learning records", e);
            return Result.fail(201, "查询失败: " + e.getMessage());
        }
    }

    /**
     * 更新学习记录 / Update learning record
     * 
     * <p>记录学生的学习进度、完成率和掌握程度</p>
     * <p>Record student's learning progress, completion rate, and mastery level</p>
     * 
     * @param record 学习记录 / Learning record
     * @return 是否更新成功 / Whether update was successful
     */
    @PostMapping("/learning-records")
    @Operation(summary = "更新学习记录 / Update learning record",
               description = "记录或更新学生的学习进度 / Record or update student's learning progress")
    public Result<Boolean> updateLearningRecord(
            @RequestBody @Parameter(description = "学习记录 / Learning record") StudentLearningRecord record) {
        
        log.info("更新学习记录 / Updating learning record: studentId={}, resourceId={}", 
                record.getStudentId(), record.getResourceId());
        
        try {
            boolean success = recommendationService.updateLearningRecord(record);
            
            if (success) {
                log.info("学习记录更新成功 / Learning record updated successfully");
                return Result.ok(true);
            } else {
                return Result.fail(210, "学习记录更新失败 / Failed to update learning record");
            }
            
        } catch (Exception e) {
            log.error("学习记录更新失败 / Learning record update failed", e);
            return Result.fail(210, "更新失败: " + e.getMessage());
        }
    }

    /**
     * 分析学生薄弱知识点 / Analyze student weak knowledge points
     * 
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID / Subject ID
     * @return 薄弱知识点及掌握度 / Weak knowledge points and mastery levels
     */
    @GetMapping("/weak-points/{studentId}/{subjectId}")
    @Operation(summary = "分析薄弱知识点 / Analyze weak points",
               description = "分析学生在某科目的薄弱知识点 / Analyze student's weak knowledge points in a subject")
    public Result<Map<Integer, Double>> analyzeWeakPoints(
            @PathVariable @Parameter(description = "学生ID / Student ID") Integer studentId,
            @PathVariable @Parameter(description = "科目ID / Subject ID") Integer subjectId) {
        
        log.info("分析薄弱知识点 / Analyzing weak points: studentId={}, subjectId={}", studentId, subjectId);
        
        try {
            Map<Integer, Double> weakPoints = recommendationService.analyzeWeakKnowledgePoints(
                    studentId, subjectId);
            
            return Result.ok(weakPoints);
            
        } catch (Exception e) {
            log.error("分析薄弱知识点失败 / Failed to analyze weak points", e);
            return Result.fail(201, "分析失败: " + e.getMessage());
        }
    }

    /**
     * 获取热门学习资源 / Get popular learning resources
     * 
     * @param subjectId 科目ID / Subject ID
     * @param resourceType 资源类型（可选） / Resource type (optional)
     * @param limit 返回数量，默认10 / Return count, default 10
     * @return 热门资源列表 / List of popular resources
     */
    @GetMapping("/popular-resources")
    @Operation(summary = "获取热门资源 / Get popular resources",
               description = "获取最受欢迎的学习资源 / Get most popular learning resources")
    public Result<List<LearningResourceVo>> getPopularResources(
            @RequestParam @Parameter(description = "科目ID / Subject ID") Integer subjectId,
            @RequestParam(required = false) @Parameter(description = "资源类型 / Type") String resourceType,
            @RequestParam(defaultValue = "10") @Parameter(description = "返回数量 / Limit") Integer limit) {
        
        log.info("获取热门资源 / Getting popular resources: subjectId={}, type={}", subjectId, resourceType);
        
        try {
            List<LearningResourceVo> resources = recommendationService.getPopularResources(
                    subjectId, resourceType, limit);
            
            return Result.ok(resources);
            
        } catch (Exception e) {
            log.error("获取热门资源失败 / Failed to get popular resources", e);
            return Result.fail(201, "获取失败: " + e.getMessage());
        }
    }

    /**
     * 获取推荐算法统计信息 / Get recommendation algorithm statistics
     * 
     * @param algorithmType 算法类型 / Algorithm type
     * @param startDate 开始日期 / Start date
     * @param endDate 结束日期 / End date
     * @return 统计信息 / Statistics
     */
    @GetMapping("/algorithm-stats")
    @Operation(summary = "算法统计 / Algorithm statistics",
               description = "获取推荐算法的效果统计 / Get effectiveness statistics of recommendation algorithms")
    public Result<Map<String, Object>> getAlgorithmStatistics(
            @RequestParam @Parameter(description = "算法类型 / Algorithm type") String algorithmType,
            @RequestParam(required = false) @Parameter(description = "开始日期 / Start date") String startDate,
            @RequestParam(required = false) @Parameter(description = "结束日期 / End date") String endDate) {
        
        log.info("查询算法统计 / Querying algorithm stats: type={}", algorithmType);
        
        try {
            Map<String, Object> stats = recommendationService.getAlgorithmStatistics(
                    algorithmType, startDate, endDate);
            
            return Result.ok(stats);
            
        } catch (Exception e) {
            log.error("查询算法统计失败 / Failed to query algorithm stats", e);
            return Result.fail(201, "查询失败: " + e.getMessage());
        }
    }

    /**
     * 批量推荐资源（适用于班级推荐） / Batch recommend resources (for class recommendations)
     * 
     * @param studentIds 学生ID列表 / List of student IDs
     * @param subjectId 科目ID / Subject ID
     * @param limit 每个学生的推荐数量 / Number of recommendations per student
     * @return 学生ID到推荐结果的映射 / Map of student ID to recommendation results
     */
    @PostMapping("/batch-recommend")
    @Operation(summary = "批量推荐 / Batch recommend",
               description = "为多个学生同时生成推荐 / Generate recommendations for multiple students")
    public Result<Map<Integer, List<RecommendationResultVo>>> batchRecommend(
            @RequestParam @Parameter(description = "学生ID列表 / Student IDs") List<Integer> studentIds,
            @RequestParam @Parameter(description = "科目ID / Subject ID") Integer subjectId,
            @RequestParam(defaultValue = "5") @Parameter(description = "推荐数量 / Limit") Integer limit) {
        
        log.info("批量推荐 / Batch recommendation: {} students, subjectId={}", studentIds.size(), subjectId);
        
        try {
            Map<Integer, List<RecommendationResultVo>> results = 
                    recommendationService.batchRecommendResources(studentIds, subjectId, limit);
            
            return Result.ok(results);
            
        } catch (Exception e) {
            log.error("批量推荐失败 / Batch recommendation failed", e);
            return Result.fail(201, "批量推荐失败: " + e.getMessage());
        }
    }

    /**
     * 保存自适应组卷配置模板 / Save adaptive paper configuration template
     * 
     * @param config 配置对象 / Configuration object
     * @return 是否保存成功 / Whether saving was successful
     */
    @PostMapping("/adaptive-config")
    @Operation(summary = "保存组卷配置 / Save paper config",
               description = "保存自适应组卷配置模板 / Save adaptive paper configuration template")
    public Result<Boolean> saveAdaptiveConfig(
            @RequestBody @Parameter(description = "配置对象 / Configuration") AdaptivePaperConfig config) {
        
        log.info("保存组卷配置 / Saving adaptive config: {}", config.getConfigName());
        
        try {
            boolean success = recommendationService.saveAdaptiveConfig(config);
            
            if (success) {
                log.info("配置保存成功 / Config saved successfully");
                return Result.ok(true);
            } else {
                return Result.fail(209, "配置保存失败 / Failed to save config");
            }
            
        } catch (Exception e) {
            log.error("配置保存失败 / Config save failed", e);
            return Result.fail(209, "保存失败: " + e.getMessage());
        }
    }

    /**
     * 获取配置模板列表 / Get configuration template list
     * 
     * @param subjectId 科目ID（可选） / Subject ID (optional)
     * @param isTemplate 是否只查询模板 / Whether to query only templates
     * @return 配置列表 / List of configurations
     */
    @GetMapping("/adaptive-configs")
    @Operation(summary = "获取配置列表 / Get config list",
               description = "获取自适应组卷配置模板列表 / Get list of adaptive paper configuration templates")
    public Result<List<AdaptivePaperConfig>> getAdaptiveConfigs(
            @RequestParam(required = false) @Parameter(description = "科目ID / Subject ID") Integer subjectId,
            @RequestParam(required = false) @Parameter(description = "只查询模板 / Only templates") Boolean isTemplate) {
        
        log.info("获取配置列表 / Getting config list: subjectId={}, isTemplate={}", subjectId, isTemplate);
        
        try {
            List<AdaptivePaperConfig> configs = recommendationService.getAdaptiveConfigs(
                    subjectId, isTemplate);
            
            return Result.ok(configs);
            
        } catch (Exception e) {
            log.error("获取配置列表失败 / Failed to get config list", e);
            return Result.fail(201, "获取失败: " + e.getMessage());
        }
    }
}
