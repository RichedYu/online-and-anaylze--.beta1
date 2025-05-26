package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.exam.enums.LevelEnum;
import com.exam.enums.QuestionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName question
 */
@Schema(description = "题目表")
@TableName(value ="question")
@Data
public class Question extends BaseEntity {

    /**
     * 问题
     */
    @Schema(description = "问题")
    private String question;

    /**
     * 问题解析
     */
    @Schema(description = "问题解析")
    private String analysis;

    /**
     * 分值
     */
    @Schema(description = "分值")
    private Integer scoreValue;

    /**
     * 1：单选题，2：多选题，3：判断题， 4：填空题，5：简答题，6：应用题
     */
    @Schema(description = "题型 1：单选题 2：多选题 3：判断题，4：填空题，5：简答题，6：应用题")
    private QuestionType type;

    /**
     * 章节ID
     */
    @Schema(description = "章节ID")
    private Long chapterId;

    /**
     * 难度级别 1~5 简单、普通、中等、较难、困难
     */
    @Schema(description = "难度级别 1~5 简单、普通、中等、较难、困难")
    private LevelEnum level;

    /**
     * 知识点集
     */
    @Schema(description = "知识点集")
    private String knowledgePointIds;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}