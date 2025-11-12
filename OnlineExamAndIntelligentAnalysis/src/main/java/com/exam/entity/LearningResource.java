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

/**
 * 学习资源实体类
 * Learning Resource Entity
 * 
 * 功能说明 / Function Description:
 * - 存储各类学习资源(视频、文档、练习题、文章等)
 * - Stores various learning resources (videos, documents, exercises, articles, etc.)
 * - 关联知识点和科目
 * - Associates with knowledge points and subjects
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "learning_resource", autoResultMap = true)
public class LearningResource extends BaseEntity {
    
    /**
     * 主键ID / Primary key ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 资源名称 / Resource name
     */
    private String resourceName;
    
    /**
     * 资源类型 / Resource type
     * 枚举值: VIDEO(视频), DOCUMENT(文档), EXERCISE(练习), ARTICLE(文章)
     * Enum: VIDEO, DOCUMENT, EXERCISE, ARTICLE
     */
    private String resourceType;
    
    /**
     * 科目ID / Subject ID
     */
    private Long subjectId;
    
    /**
     * 关联知识点ID列表(JSON格式) / Related knowledge point IDs (JSON format)
     * 示例 / Example: [1, 2, 3, 5]
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> knowledgePointIds;
    
    /**
     * 难度等级 / Difficulty level
     * 枚举值: EASY(简单), MEDIUM(中等), HARD(困难)
     * Enum: EASY, MEDIUM, HARD
     */
    private String difficultyLevel;
    
    /**
     * 资源URL / Resource URL
     */
    private String resourceUrl;
    
    /**
     * 资源描述 / Resource description
     */
    private String description;
    
    /**
     * 标签列表(JSON格式) / Tags list (JSON format)
     * 示例 / Example: ["微积分", "基础", "视频教程"]
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> tags;
    
    /**
     * 学习时长(分钟) / Duration in minutes
     */
    private Integer durationMinutes;
    
    /**
     * 浏览次数 / View count
     */
    private Integer viewCount;
    
    /**
     * 点赞数 / Like count
     */
    private Integer likeCount;
    
    /**
     * 有效性评分(0-1) / Effectiveness score (0-1)
     * 基于学生学习后的成绩提升计算
     * Calculated based on student score improvement after learning
     */
    private BigDecimal effectivenessScore;
    
    /**
     * 是否启用 / Is active
     * 1-启用 0-禁用 / 1-active 0-inactive
     */
    private Integer isActive;
    
    // ========== 枚举定义 / Enum Definitions ==========
    
    /**
     * 资源类型枚举 / Resource Type Enum
     */
    public enum ResourceType {
        VIDEO("视频 / Video"),
        DOCUMENT("文档 / Document"),
        EXERCISE("练习题 / Exercise"),
        ARTICLE("文章 / Article"),
        INTERACTIVE("互动课程 / Interactive Course");
        
        private final String description;
        
        ResourceType(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 难度等级枚举 / Difficulty Level Enum
     */
    public enum DifficultyLevel {
        EASY("简单 / Easy"),
        MEDIUM("中等 / Medium"),
        HARD("困难 / Hard");
        
        private final String description;
        
        DifficultyLevel(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
}