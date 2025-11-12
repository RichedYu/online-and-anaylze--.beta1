package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.AdaptivePaperConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 自适应组卷配置Mapper接口
 * Adaptive Paper Configuration Mapper Interface
 * 
 * 提供自适应组卷配置的数据库操作方法
 * Provides database operation methods for adaptive paper configuration
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Mapper
public interface AdaptivePaperConfigMapper extends BaseMapper<AdaptivePaperConfig> {

    /**
     * 查询学生的组卷配置 / Query student's paper configurations
     * @param studentId 学生ID / Student ID
     * @return 配置列表 / Configuration list
     */
    @Select("SELECT * FROM adaptive_paper_config " +
            "WHERE student_id = #{studentId} " +
            "ORDER BY create_time DESC")
    List<AdaptivePaperConfig> selectByStudentId(@Param("studentId") Long studentId);

    /**
     * 查询模板配置 / Query template configurations
     * @param subjectId 科目ID(可选) / Subject ID (optional)
     * @return 配置列表 / Configuration list
     */
    @Select("<script>" +
            "SELECT * FROM adaptive_paper_config " +
            "WHERE is_template = 1 " +
            "<if test='subjectId != null'>" +
            "AND subject_id = #{subjectId} " +
            "</if>" +
            "ORDER BY usage_count DESC, create_time DESC" +
            "</script>")
    List<AdaptivePaperConfig> selectTemplates(@Param("subjectId") Long subjectId);

    /**
     * 查询热门配置(按使用次数) / Query popular configurations (by usage count)
     * @param subjectId 科目ID / Subject ID
     * @param limit 返回数量 / Return count
     * @return 配置列表 / Configuration list
     */
    @Select("SELECT * FROM adaptive_paper_config " +
            "WHERE subject_id = #{subjectId} AND is_template = 1 " +
            "ORDER BY usage_count DESC " +
            "LIMIT #{limit}")
    List<AdaptivePaperConfig> selectPopularConfigs(@Param("subjectId") Long subjectId,
                                                    @Param("limit") Integer limit);

    /**
     * 根据科目和难度查询配置 / Query configurations by subject and difficulty
     * @param subjectId 科目ID / Subject ID
     * @param targetDifficulty 目标难度 / Target difficulty
     * @return 配置列表 / Configuration list
     */
    @Select("SELECT * FROM adaptive_paper_config " +
            "WHERE subject_id = #{subjectId} " +
            "AND target_difficulty = #{targetDifficulty} " +
            "AND is_template = 1 " +
            "ORDER BY usage_count DESC")
    List<AdaptivePaperConfig> selectBySubjectAndDifficulty(@Param("subjectId") Long subjectId,
                                                           @Param("targetDifficulty") String targetDifficulty);
}