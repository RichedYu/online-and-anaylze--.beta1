package com.exam.service;

import com.exam.entity.AdaptivePaperConfig;
import com.exam.entity.RecommendationHistory;
import com.exam.entity.StudentLearningRecord;
import com.exam.vo.LearningResourceVo;
import com.exam.vo.RecommendationRequestVo;
import com.exam.vo.RecommendationResultVo;

import java.util.List;
import java.util.Map;

/**
 * 智能推荐服务接口 / Intelligent Recommendation Service Interface
 * 
 * <p>提供个性化学习资源推荐、自适应组卷和学习进度追踪等功能</p>
 * <p>Provides personalized learning resource recommendations, adaptive paper generation, 
 * and learning progress tracking</p>
 * 
 * @author System
 * @since 2025-11-12
 */
public interface IntelligentRecommendationService {

    /**
     * 获取个性化学习资源推荐 / Get personalized learning resource recommendations
     * 
     * <p>根据学生的学习记录、薄弱知识点和偏好，推荐最合适的学习资源</p>
     * <p>Recommend the most suitable learning resources based on student's learning records, 
     * weak knowledge points, and preferences</p>
     * 
     * @param request 推荐请求参数 / Recommendation request parameters
     * @return 推荐结果列表，按推荐度排序 / List of recommendation results, sorted by relevance
     */
    List<RecommendationResultVo> recommendResources(RecommendationRequestVo request);

    /**
     * 生成自适应试卷 / Generate adaptive paper
     * 
     * <p>根据学生的学习情况和目标难度，智能生成个性化试卷</p>
     * <p>Intelligently generate personalized papers based on student's learning status 
     * and target difficulty</p>
     * 
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID / Subject ID
     * @param config 组卷配置 / Paper configuration
     * @return 组卷配置结果（包含题目分配详情） / Paper configuration result with question allocation details
     */
    AdaptivePaperConfig generateAdaptivePaper(Integer studentId, Integer subjectId, AdaptivePaperConfig config);

    /**
     * 获取学生推荐历史 / Get student recommendation history
     * 
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID（可选，为null时查询所有科目） / Subject ID (optional, null for all subjects)
     * @param limit 返回记录数量 / Number of records to return
     * @return 推荐历史列表 / List of recommendation history
     */
    List<RecommendationHistory> getRecommendationHistory(Integer studentId, Integer subjectId, Integer limit);

    /**
     * 记录用户对推荐的反馈 / Record user feedback on recommendations
     * 
     * @param recommendationId 推荐历史ID / Recommendation history ID
     * @param feedbackScore 反馈评分（1-5分） / Feedback score (1-5)
     * @param feedbackComment 反馈评论 / Feedback comment
     * @return 是否记录成功 / Whether recording was successful
     */
    boolean recordFeedback(Integer recommendationId, Integer feedbackScore, String feedbackComment);

    /**
     * 获取学生学习记录 / Get student learning records
     * 
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID（可选） / Subject ID (optional)
     * @return 学习记录列表 / List of learning records
     */
    List<StudentLearningRecord> getStudentLearningRecords(Integer studentId, Integer subjectId);

    /**
     * 更新学习记录 / Update learning record
     * 
     * <p>记录学生的学习进度、完成率和掌握程度</p>
     * <p>Record student's learning progress, completion rate, and mastery level</p>
     * 
     * @param record 学习记录 / Learning record
     * @return 是否更新成功 / Whether update was successful
     */
    boolean updateLearningRecord(StudentLearningRecord record);

    /**
     * 分析学生薄弱知识点 / Analyze student weak knowledge points
     * 
     * <p>基于考试成绩和答题记录，识别学生的薄弱知识点</p>
     * <p>Identify student's weak knowledge points based on exam scores and answer records</p>
     * 
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID / Subject ID
     * @return 薄弱知识点ID列表及掌握度 / Map of weak knowledge point IDs and mastery levels
     */
    Map<Integer, Double> analyzeWeakKnowledgePoints(Integer studentId, Integer subjectId);

    /**
     * 获取热门学习资源 / Get popular learning resources
     * 
     * @param subjectId 科目ID / Subject ID
     * @param resourceType 资源类型（可选） / Resource type (optional)
     * @param limit 返回数量 / Number of resources to return
     * @return 热门资源列表 / List of popular resources
     */
    List<LearningResourceVo> getPopularResources(Integer subjectId, String resourceType, Integer limit);

    /**
     * 获取推荐算法统计信息 / Get recommendation algorithm statistics
     * 
     * <p>用于分析不同推荐算法的效果</p>
     * <p>Used to analyze the effectiveness of different recommendation algorithms</p>
     * 
     * @param algorithmType 算法类型 / Algorithm type
     * @param startDate 开始日期 / Start date
     * @param endDate 结束日期 / End date
     * @return 统计信息（平均评分、推荐次数等） / Statistics (average score, recommendation count, etc.)
     */
    Map<String, Object> getAlgorithmStatistics(String algorithmType, String startDate, String endDate);

    /**
     * 批量推荐资源（适用于班级推荐） / Batch recommend resources (for class recommendations)
     * 
     * @param studentIds 学生ID列表 / List of student IDs
     * @param subjectId 科目ID / Subject ID
     * @param limit 每个学生的推荐数量 / Number of recommendations per student
     * @return 学生ID到推荐结果的映射 / Map of student ID to recommendation results
     */
    Map<Integer, List<RecommendationResultVo>> batchRecommendResources(List<Integer> studentIds, Integer subjectId, Integer limit);

    /**
     * 保存自适应组卷配置模板 / Save adaptive paper configuration template
     * 
     * @param config 配置对象 / Configuration object
     * @return 是否保存成功 / Whether saving was successful
     */
    boolean saveAdaptiveConfig(AdaptivePaperConfig config);

    /**
     * 获取配置模板列表 / Get configuration template list
     * 
     * @param subjectId 科目ID（可选） / Subject ID (optional)
     * @param isTemplate 是否只查询模板 / Whether to query only templates
     * @return 配置列表 / List of configurations
     */
    List<AdaptivePaperConfig> getAdaptiveConfigs(Integer subjectId, Boolean isTemplate);
}