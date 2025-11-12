package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 学生学习记录实体类
 * Student Learning Record Entity
 * 
 * 功能说明 / Function Description:
 * - 追踪学生对学习资源的学习进度和效果
 * - Tracks student learning progress and effectiveness on resources
 * - 记录学习时长、完成率、掌握程度等信息
 * - Records learning duration, completion rate, mastery level, etc.
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "student_learning_record")
public class StudentLearningRecord extends BaseEntity {
    
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
     * 资源ID / Resource ID
     */
    private Long resourceId;
    
    /**
     * 开始学习时间 / Start time
     */
    private LocalDateTime startTime;
    
    /**
     * 最后访问时间 / Last access time
     */
    private LocalDateTime lastAccessTime;
    
    /**
     * 总学习时长(分钟) / Total duration in minutes
     * 累计学习该资源的总时间
     * Cumulative total time spent on this resource
     */
    private Integer totalDurationMinutes;
    
    /**
     * 完成率(%) / Completion rate (%)
     * 0-100之间的百分比值
     * Percentage value between 0-100
     */
    private BigDecimal completionRate;
    
    /**
     * 是否完成 / Is completed
     * 1-已完成 0-未完成 / 1-completed 0-not completed
     */
    private Integer isCompleted;
    
    /**
     * 掌握程度 / Mastery level
     * 枚举值: NOT_STARTED(未开始), LEARNING(学习中), MASTERED(已掌握)
     * Enum: NOT_STARTED, LEARNING, MASTERED
     */
    private String masteryLevel;
    
    /**
     * 练习次数 / Practice count
     * 对于练习类资源，记录完成练习的次数
     * For exercise resources, records the number of practice attempts
     */
    private Integer practiceCount;
    
    /**
     * 练习正确率(%) / Practice accuracy (%)
     * 0-100之间的百分比值
     * Percentage value between 0-100
     */
    private BigDecimal practiceAccuracy;
    
    /**
     * 学习笔记 / Learning notes
     * 学生记录的笔记内容
     * Notes recorded by the student
     */
    private String notes;
    
    // ========== 枚举定义 / Enum Definitions ==========
    
    /**
     * 掌握程度枚举 / Mastery Level Enum
     */
    public enum MasteryLevel {
        NOT_STARTED("未开始 / Not Started"),
        LEARNING("学习中 / Learning"),
        MASTERED("已掌握 / Mastered");
        
        private final String description;
        
        MasteryLevel(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
}