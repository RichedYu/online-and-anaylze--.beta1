package com.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 推荐结果VO
 * Recommendation Result VO
 * 
 * 功能说明 / Function Description:
 * - 封装推荐结果信息
 * - Encapsulates recommendation result information
 * - 包含推荐理由和置信度
 * - Includes recommendation reason and confidence score
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "推荐结果VO // Recommendation Result VO")
public class RecommendationResultVo {

    /**
     * 推荐ID(如果已保存) / Recommendation ID (if saved)
     */
    @Schema(description = "推荐ID // Recommendation ID")
    private Long recommendationId;

    /**
     * 资源ID / Resource ID
     */
    @Schema(description = "资源ID // Resource ID")
    private Long resourceId;

    /**
     * 资源名称 / Resource name
     */
    @Schema(description = "资源名称 // Resource name")
    private String resourceName;

    /**
     * 资源类型 / Resource type
     */
    @Schema(description = "资源类型 // Resource type", example = "VIDEO")
    private String resourceType;

    /**
     * 难度等级 / Difficulty level
     */
    @Schema(description = "难度等级 // Difficulty level", example = "MEDIUM")
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
     * 学习时长(分钟) / Duration in minutes
     */
    @Schema(description = "学习时长(分钟) // Duration")
    private Integer durationMinutes;

    /**
     * 有效性评分(0-1) / Effectiveness score (0-1)
     */
    @Schema(description = "有效性评分 // Effectiveness score")
    private BigDecimal effectivenessScore;

    /**
     * 推荐理由(AI生成) / Recommendation reason (AI generated)
     */
    @Schema(description = "推荐理由 // Recommendation reason")
    private String recommendationReason;

    /**
     * 推荐置信度(0-1) / Confidence score (0-1)
     */
    @Schema(description = "推荐置信度 // Confidence score")
    private BigDecimal confidenceScore;

    /**
     * 匹配的薄弱知识点 / Matched weak knowledge points
     */
    @Schema(description = "匹配的薄弱知识点 // Matched weak points")
    private List<Map<String, Object>> matchedWeakPoints;

    /**
     * 推荐算法 / Recommendation algorithm
     */
    @Schema(description = "推荐算法 // Algorithm", example = "AI")
    private String algorithm;

    /**
     * 推荐排序(1-N) / Recommendation rank (1-N)
     */
    @Schema(description = "推荐排序 // Rank")
    private Integer rank;

    /**
     * 是否已学习 / Is learned
     */
    @Schema(description = "是否已学习 // Is learned")
    private Boolean isLearned;

    /**
     * 学习进度(%) / Learning progress (%)
     */
    @Schema(description = "学习进度 // Progress")
    private BigDecimal learningProgress;

    /**
     * 标签列表 / Tags list
     */
    @Schema(description = "标签列表 // Tags")
    private List<String> tags;
}