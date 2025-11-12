package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.RecommendationHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 推荐历史Mapper接口
 * Recommendation History Mapper Interface
 * 
 * 提供推荐历史的数据库操作方法
 * Provides database operation methods for recommendation history
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Mapper
public interface RecommendationHistoryMapper extends BaseMapper<RecommendationHistory> {

    /**
     * 查询学生的推荐历史 / Query student's recommendation history
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID(可选) / Subject ID (optional)
     * @return 推荐历史列表 / Recommendation history list
     */
    @Select("<script>" +
            "SELECT * FROM recommendation_history " +
            "WHERE student_id = #{studentId} " +
            "<if test='subjectId != null'>" +
            "AND subject_id = #{subjectId} " +
            "</if>" +
            "ORDER BY create_time DESC " +
            "LIMIT 50" +
            "</script>")
    List<RecommendationHistory> selectByStudentAndSubject(@Param("studentId") Long studentId,
                                                           @Param("subjectId") Long subjectId);

    /**
     * 查询学生接受的推荐 / Query student's accepted recommendations
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID / Subject ID
     * @return 推荐历史列表 / Recommendation history list
     */
    @Select("SELECT * FROM recommendation_history " +
            "WHERE student_id = #{studentId} AND subject_id = #{subjectId} " +
            "AND is_accepted = 1 " +
            "ORDER BY create_time DESC")
    List<RecommendationHistory> selectAcceptedRecommendations(@Param("studentId") Long studentId,
                                                               @Param("subjectId") Long subjectId);

    /**
     * 获取算法性能统计 / Get algorithm performance statistics
     * @param algorithmType 算法类型 / Algorithm type
     * @param subjectId 科目ID / Subject ID
     * @return 统计信息 / Statistics
     */
    @Select("SELECT " +
            "COUNT(*) as total_count, " +
            "SUM(CASE WHEN is_accepted = 1 THEN 1 ELSE 0 END) as accepted_count, " +
            "ROUND(SUM(CASE WHEN is_accepted = 1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) as acceptance_rate, " +
            "AVG(feedback_score) as avg_feedback_score, " +
            "AVG(confidence_score) as avg_confidence_score " +
            "FROM recommendation_history " +
            "WHERE algorithm_type = #{algorithmType} AND subject_id = #{subjectId} " +
            "AND create_time >= DATE_SUB(NOW(), INTERVAL 30 DAY)")
    Map<String, Object> selectAlgorithmStats(@Param("algorithmType") String algorithmType,
                                             @Param("subjectId") Long subjectId);

    /**
     * 获取学生推荐效果统计 / Get student recommendation effectiveness
     * @param studentId 学生ID / Student ID
     * @param subjectId 科目ID / Subject ID
     * @return 统计信息 / Statistics
     */
    @Select("SELECT " +
            "COUNT(*) as total_recommendations, " +
            "SUM(CASE WHEN is_accepted = 1 THEN 1 ELSE 0 END) as accepted_count, " +
            "SUM(CASE WHEN is_completed = 1 THEN 1 ELSE 0 END) as completed_count, " +
            "AVG(feedback_score) as avg_feedback_score, " +
            "AVG(time_spent_minutes) as avg_time_spent " +
            "FROM recommendation_history " +
            "WHERE student_id = #{studentId} AND subject_id = #{subjectId}")
    Map<String, Object> selectStudentStats(@Param("studentId") Long studentId,
                                           @Param("subjectId") Long subjectId);
}