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
import java.util.Map;

/**
 * 自适应组卷配置实体类
 * Adaptive Paper Configuration Entity
 * 
 * 功能说明 / Function Description:
 * - 存储智能组卷的配置参数
 * - Stores intelligent paper generation configuration parameters
 * - 支持基于学生能力的动态试卷生成
 * - Supports dynamic paper generation based on student ability
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "adaptive_paper_config", autoResultMap = true)
public class AdaptivePaperConfig extends BaseEntity {
    
    /**
     * 主键ID / Primary key ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 配置名称 / Config name
     */
    private String configName;
    
    /**
     * 目标学生ID / Target student ID
     * 可为空，表示这是一个通用模板配置
     * Can be null, indicating this is a generic template configuration
     */
    private Long studentId;
    
    /**
     * 科目ID / Subject ID
     */
    private Long subjectId;
    
    /**
     * 目标难度 / Target difficulty
     * 枚举值: EASY(简单), MEDIUM(中等), HARD(困难), ADAPTIVE(自适应)
     * Enum: EASY, MEDIUM, HARD, ADAPTIVE
     */
    private String targetDifficulty;
    
    /**
     * 题型分布(JSON格式) / Question type distribution (JSON format)
     * 示例 / Example: {"单选题": 10, "多选题": 5, "填空题": 5, "计算题": 3}
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Integer> questionDistribution;
    
    /**
     * 知识点权重(JSON格式) / Knowledge point weights (JSON format)
     * 示例 / Example: {"1": 0.3, "2": 0.2, "3": 0.5}
     * 键为知识点ID，值为权重(0-1)
     * Key is knowledge point ID, value is weight (0-1)
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, BigDecimal> knowledgePointWeights;
    
    /**
     * 总分 / Total score
     */
    private Integer totalScore;
    
    /**
     * 时间限制(分钟) / Time limit in minutes
     */
    private Integer timeLimitMinutes;
    
    /**
     * 自适应策略 / Adaptation strategy
     * 枚举值: IRT(项目反应理论), CAT(计算机自适应测试), KNOWLEDGE_GRAPH(知识图谱)
     * Enum: IRT, CAT, KNOWLEDGE_GRAPH
     */
    private String adaptationStrategy;
    
    /**
     * 是否包含薄弱点 / Include weak points
     * 1-包含 0-不包含 / 1-include 0-exclude
     */
    private Integer includeWeakPoints;
    
    /**
     * 薄弱点题目占比(0-1) / Weak point ratio (0-1)
     * 薄弱知识点相关题目的比例
     * Proportion of questions related to weak knowledge points
     */
    private BigDecimal weakPointRatio;
    
    /**
     * 复习点题目占比(0-1) / Review point ratio (0-1)
     * 需要复习的知识点相关题目的比例
     * Proportion of questions related to review points
     */
    private BigDecimal reviewPointRatio;
    
    /**
     * 挑战题占比(0-1) / Challenge ratio (0-1)
     * 高难度挑战题的比例
     * Proportion of high-difficulty challenge questions
     */
    private BigDecimal challengeRatio;
    
    /**
     * 是否为模板 / Is template
     * 1-模板 0-个人配置 / 1-template 0-personal config
     */
    private Integer isTemplate;
    
    /**
     * 使用次数 / Usage count
     * 记录该配置被使用的次数
     * Records the number of times this config has been used
     */
    private Integer usageCount;
    
    /**
     * 平均分 / Average score
     * 使用该配置生成的试卷的平均分
     * Average score of papers generated using this config
     */
    private BigDecimal avgScore;
    
    // ========== 枚举定义 / Enum Definitions ==========
    
    /**
     * 目标难度枚举 / Target Difficulty Enum
     */
    public enum TargetDifficulty {
        EASY("简单 / Easy"),
        MEDIUM("中等 / Medium"),
        HARD("困难 / Hard"),
        ADAPTIVE("自适应 / Adaptive");
        
        private final String description;
        
        TargetDifficulty(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 自适应策略枚举 / Adaptation Strategy Enum
     */
    public enum AdaptationStrategy {
        IRT("项目反应理论 / Item Response Theory"),
        CAT("计算机自适应测试 / Computerized Adaptive Testing"),
        KNOWLEDGE_GRAPH("知识图谱 / Knowledge Graph");
        
        private final String description;
        
        AdaptationStrategy(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
}