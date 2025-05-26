package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName chapter
 */
@Schema(description = "章节表")
@TableName(value ="chapter")
@Data
public class Chapter extends BaseEntity {

    /**
     * 章节名
     */
    @Schema(description = "章节名")
    private String name;

    /**
     * 课程ID
     */
    @Schema(description = "课程ID")
    private Long subjectId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}