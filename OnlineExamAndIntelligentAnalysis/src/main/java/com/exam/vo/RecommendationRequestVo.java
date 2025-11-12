package com.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学习资源推荐请求VO
 * Learning Resource Recommendation Request VO
 * 
 * 功能说明 / Function Description:
 * - 封装推荐请求参数
 * - Encapsulates recommendation request parameters
 * - 支持多种推荐算法选择
 * - Supports multiple recommendation algorithm selection
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "推荐请求VO // Recommendation Request VO")
public class RecommendationRequestVo {

    /**
     * 学生ID / Student ID
     */
    @Schema(description = "学生ID // Student ID", required = true)
    private Long studentId;

    /**
     * 科目ID / Subject ID
     */
    @Schema(description = "科目ID // Subject ID", required = true)
    private Long subjectId;

    /**
     * 推荐数量 / Number of recommendations
     * 默认返回5个推荐 / Default returns 5 recommendations
     */
    @Schema(description = "推荐数量 // Recommendation count", example = "5")
    private Integer limit = 5;

    /**
     * 推荐算法 / Recommendation algorithm
     * 枚举值 / Enum values:
     * - COLLABORATIVE: 协同过滤 / Collaborative Filtering
     * - CONTENT_BASED: 基于内容 / Content-Based
     * - AI: AI推荐 / AI-Powered
     * - HYBRID: 混合推荐 / Hybrid
     */
    @Schema(description = "推荐算法 // Algorithm", 
            example = "AI", 
            allowableValues = {"COLLABORATIVE", "CONTENT_BASED", "AI", "HYBRID"})
    private String algorithm = "AI";

    /**
     * 资源类型过滤(可选) / Resource type filter (optional)
     * 可选值: VIDEO, DOCUMENT, EXERCISE, ARTICLE
     * Optional values: VIDEO, DOCUMENT, EXERCISE, ARTICLE
     */
    @Schema(description = "资源类型过滤 // Resource type filter", 
            example = "VIDEO",
            allowableValues = {"VIDEO", "DOCUMENT", "EXERCISE", "ARTICLE"})
    private String resourceType;

    /**
     * 难度级别过滤(可选) / Difficulty level filter (optional)
     * 可选值: EASY, MEDIUM, HARD
     * Optional values: EASY, MEDIUM, HARD
     */
    @Schema(description = "难度级别过滤 // Difficulty filter", 
            example = "MEDIUM",
            allowableValues = {"EASY", "MEDIUM", "HARD"})
    private String difficultyLevel;

    /**
     * 是否只推荐未学习的资源 / Only recommend unlearned resources
     */
    @Schema(description = "是否只推荐未学习的资源 // Only unlearned", example = "true")
    private Boolean unlearnedOnly = false;

    /**
     * 最小有效性评分(0-1) / Minimum effectiveness score (0-1)
     * 只推荐评分高于此值的资源
     * Only recommend resources with score above this value
     */
    @Schema(description = "最小有效性评分 // Min effectiveness score", example = "0.6")
    private Double minEffectivenessScore = 0.0;
}