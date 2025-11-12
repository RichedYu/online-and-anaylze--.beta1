package com.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 学习资源VO
 * Learning Resource VO
 * 
 * 功能说明 / Function Description:
 * - 用于前端展示学习资源信息
 * - For frontend display of learning resource information
 * - 包含资源的详细属性和统计数据
 * - Includes detailed properties and statistical data
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学习资源VO // Learning Resource VO")
public class LearningResourceVo {

    /**
     * 资源ID / Resource ID
     */
    @Schema(description = "资源ID // Resource ID")
    private Long id;

    /**
     * 资源名称 / Resource name
     */
    @Schema(description = "资源名称 // Resource name")
    private String resourceName;

    /**
     * 资源类型 / Resource type
     */
    @Schema(description = "资源类型 // Resource type", 
            example = "VIDEO",
            allowableValues = {"VIDEO", "DOCUMENT", "EXERCISE", "ARTICLE", "INTERACTIVE"})
    private String resourceType;

    /**
     * 科目ID / Subject ID
     */
    @Schema(description = "科目ID // Subject ID")
    private Long subjectId;

    /**
     * 科目名称 / Subject name
     */
    @Schema(description = "科目名称 // Subject name")
    private String subjectName;

    /**
     * 关联知识点ID列表 / Related knowledge point IDs
     */
    @Schema(description = "关联知识点ID列表 // Knowledge point IDs")
    private List<Long> knowledgePointIds;

    /**
     * 关联知识点名称列表 / Related knowledge point names
     */
    @Schema(description = "关联知识点名称列表 // Knowledge point names")
    private List<String> knowledgePointNames;

    /**
     * 难度等级 / Difficulty level
     */
    @Schema(description = "难度等级 // Difficulty level",
            example = "MEDIUM",
            allowableValues = {"EASY", "MEDIUM", "HARD"})
    private String difficultyLevel;

    /**
     * 资源URL / Resource URL
     */
    @Schema(description = "资源URL // Resource URL")
    private String resourceUrl;

    /**
     * 资源描述 / Resource description
     */
    @Schema(description = "资源描述 // Resource description")
    private String description;

    /**
     * 标签列表 / Tags list
     */
    @Schema(description = "标签列表 // Tags")
    private List<String> tags;

    /**
     * 学习时长(分钟) / Duration in minutes
     */
    @Schema(description = "学习时长(分钟) // Duration")
    private Integer durationMinutes;

    /**
     * 浏览次数 / View count
     */
    @Schema(description = "浏览次数 // View count")
    private Integer viewCount;

    /**
     * 点赞数 / Like count
     */
    @Schema(description = "点赞数 // Like count")
    private Integer likeCount;

    /**
     * 有效性评分(0-1) / Effectiveness score (0-1)
     */
    @Schema(description = "有效性评分 // Effectiveness score")
    private BigDecimal effectivenessScore;

    /**
     * 是否启用 / Is active
     */
    @Schema(description = "是否启用 // Is active")
    private Integer isActive;

    /**
     * 创建时间 / Create time
     */
    @Schema(description = "创建时间 // Create time")
    private Date createTime;

    /**
     * 学生学习进度(%) / Student learning progress (%)
     * 如果查询包含学生信息时返回
     * Returned when query includes student information
     */
    @Schema(description = "学生学习进度 // Student progress")
    private BigDecimal studentProgress;

    /**
     * 学生掌握程度 / Student mastery level
     * 如果查询包含学生信息时返回
     * Returned when query includes student information
     */
    @Schema(description = "学生掌握程度 // Student mastery",
            example = "LEARNING",
            allowableValues = {"NOT_STARTED", "LEARNING", "MASTERED"})
    private String studentMasteryLevel;
}