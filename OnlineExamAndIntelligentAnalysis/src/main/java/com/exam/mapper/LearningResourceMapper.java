package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.LearningResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学习资源Mapper接口
 * Learning Resource Mapper Interface
 * 
 * 提供学习资源的数据库操作方法
 * Provides database operation methods for learning resources
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Mapper
public interface LearningResourceMapper extends BaseMapper<LearningResource> {

    /**
     * 根据科目和难度查询资源 / Query resources by subject and difficulty
     * @param subjectId 科目ID / Subject ID
     * @param difficultyLevel 难度等级 / Difficulty level
     * @return 资源列表 / Resource list
     */
    @Select("SELECT * FROM learning_resource WHERE subject_id = #{subjectId} " +
            "AND difficulty_level = #{difficultyLevel} AND is_active = 1 " +
            "ORDER BY effectiveness_score DESC")
    List<LearningResource> selectBySubjectAndDifficulty(@Param("subjectId") Long subjectId,
                                                         @Param("difficultyLevel") String difficultyLevel);

    /**
     * 根据知识点ID查询资源 / Query resources by knowledge point ID
     * @param knowledgePointId 知识点ID / Knowledge point ID
     * @return 资源列表 / Resource list
     */
    @Select("SELECT * FROM learning_resource " +
            "WHERE JSON_CONTAINS(knowledge_point_ids, CAST(#{knowledgePointId} AS JSON)) " +
            "AND is_active = 1 " +
            "ORDER BY effectiveness_score DESC, view_count DESC")
    List<LearningResource> selectByKnowledgePoint(@Param("knowledgePointId") Long knowledgePointId);

    /**
     * 根据多个知识点ID查询资源 / Query resources by multiple knowledge point IDs
     * @param knowledgePointIds 知识点ID列表 / Knowledge point ID list
     * @return 资源列表 / Resource list
     */
    @Select("<script>" +
            "SELECT DISTINCT lr.* FROM learning_resource lr " +
            "WHERE lr.is_active = 1 " +
            "AND (" +
            "<foreach collection='knowledgePointIds' item='kpId' separator=' OR '>" +
            "JSON_CONTAINS(lr.knowledge_point_ids, CAST(#{kpId} AS JSON))" +
            "</foreach>" +
            ") " +
            "ORDER BY lr.effectiveness_score DESC, lr.view_count DESC" +
            "</script>")
    List<LearningResource> selectByKnowledgePoints(@Param("knowledgePointIds") List<Long> knowledgePointIds);

    /**
     * 根据资源类型查询 / Query by resource type
     * @param subjectId 科目ID / Subject ID
     * @param resourceType 资源类型 / Resource type
     * @return 资源列表 / Resource list
     */
    @Select("SELECT * FROM learning_resource " +
            "WHERE subject_id = #{subjectId} AND resource_type = #{resourceType} " +
            "AND is_active = 1 " +
            "ORDER BY effectiveness_score DESC")
    List<LearningResource> selectByTypeAndSubject(@Param("subjectId") Long subjectId,
                                                   @Param("resourceType") String resourceType);

    /**
     * 获取热门资源(按浏览量) / Get popular resources (by view count)
     * @param subjectId 科目ID / Subject ID
     * @param limit 返回数量 / Return count
     * @return 资源列表 / Resource list
     */
    @Select("SELECT * FROM learning_resource " +
            "WHERE subject_id = #{subjectId} AND is_active = 1 " +
            "ORDER BY view_count DESC, like_count DESC " +
            "LIMIT #{limit}")
    List<LearningResource> selectPopularResources(@Param("subjectId") Long subjectId,
                                                   @Param("limit") Integer limit);

    /**
     * 获取高效资源(按有效性评分) / Get effective resources (by effectiveness score)
     * @param subjectId 科目ID / Subject ID
     * @param limit 返回数量 / Return count
     * @return 资源列表 / Resource list
     */
    @Select("SELECT * FROM learning_resource " +
            "WHERE subject_id = #{subjectId} AND is_active = 1 " +
            "AND effectiveness_score > 0 " +
            "ORDER BY effectiveness_score DESC " +
            "LIMIT #{limit}")
    List<LearningResource> selectEffectiveResources(@Param("subjectId") Long subjectId,
                                                     @Param("limit") Integer limit);
}