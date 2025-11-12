package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 推荐历史实体类
 * Recommendation History Entity
 * 
 * 功能说明 / Function Description:
 * - 记录系统推荐给学生的学习资源和试卷
 * - Records learning resources and papers recommended to students
 * - 跟踪用户反馈和推荐效果
 * - Tracks user feedback and recommendation effectiveness
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "recommendation_history", autoResultMap = true)
public class RecommendationHistory extends BaseEntity {
    
    /**
     * 主键ID / Primary key ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 学生ID / Student ID
     */
    private Long studentId;
    
    /**
     * 科目ID / Subject ID
     */
    private Long subjectId;
    
    /**
     * 推荐类型 / Recommendation type
     * 枚举值: RESOURCE(资源推荐), PAPER(试卷推荐), KNOWLEDGE(知识点推荐)
     * Enum: RESOURCE, PAPER, KNOWLEDGE
     */
    private String recommendationType;
    
    /**
     * 资源ID(推荐资源时使用) / Resource ID (used when recommending resources)
     */
    private Long resourceId;
    
    /**
     * 试卷ID(推荐试卷时使用) / Paper ID (used when recommending papers)
     */
    private Long paperId;
    
    /**
     * 推荐理由(AI生成) / Recommendation reason (AI generated)
     */
    private String recommendationReason;
    
    /**
     * 薄弱知识点列表(JSON格式) / Weak knowledge points list (JSON format)
     * 示例 / Example: [{"id": 1, "name": "微积分", "masteryLevel": 0.45}]
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Map<String, Object>> weakPoints;
    
    /**
     * 推荐算法类型 / Algorithm type
     * 枚举值: COLLABORATIVE(协同过滤), CONTENT_BASED(基于内容), AI(AI推荐)
     * Enum: COLLABORATIVE, CONTENT_BASED, AI
     */
    private String algorithmType;
    
    /**
     * 推荐置信度(0-1) / Confidence score (0-1)
     * 表示推荐的可靠程度
     * Indicates the reliability of the recommendation
     */
    private BigDecimal confidenceScore;
    
    /**
     * 是否接受推荐 / Is accepted
     * 1-接受 0-拒绝 NULL-未响应 / 1-accepted 0-rejected NULL-no response
     */
    private Integer isAccepted;
    
    /**
     * 用户反馈评分(1-5) / User feedback score (1-5)
     * 1-很差 2-较差 3-一般 4-较好 5-很好
     * 1-very poor 2-poor 3-average 4-good 5-excellent
     */
    private Integer feedbackScore;
    
    /**
     * 反馈评论 / Feedback comment
     */
    private String feedbackComment;
    
    /**
     * 使用时长(分钟) / Time spent in minutes
     * 记录学生实际使用推荐资源的时间
     * Records actual time spent using recommended resource
     */
    private Integer timeSpentMinutes;
    
    /**
     * 是否完成 / Is completed
     * 1-已完成 0-未完成 / 1-completed 0-not completed
     */
    private Integer isCompleted;
    
    // ========== 枚举定义 / Enum Definitions ==========
    
    /**
     * 推荐类型枚举 / Recommendation Type Enum
     */
    public enum RecommendationType {
        RESOURCE("学习资源推荐 / Learning Resource"),
        PAPER("试卷推荐 / Paper Recommendation"),
        KNOWLEDGE("知识点推荐 / Knowledge Point");
        
        private final String description;
        
        RecommendationType(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 推荐算法类型枚举 / Algorithm Type Enum
     */
    public enum AlgorithmType {
        COLLABORATIVE("协同过滤 / Collaborative Filtering"),
        CONTENT_BASED("基于内容 / Content-Based"),
        AI("AI推荐 / AI-Powered"),
        HYBRID("混合推荐 / Hybrid");
        
        private final String description;
        
        AlgorithmType(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
}