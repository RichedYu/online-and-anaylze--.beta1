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
 * @TableName student
 */
@Schema(description = "学生表")
@TableName(value ="student")
@Data
public class Student extends BaseEntity {

    /**
     * 学号
     */
    @Schema(description = "学号")
    private Long studentId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 班级ID
     */
    @Schema(description = "班级ID")
    private Long classId;

    /**
     * 学院ID
     */
    @Schema(description = "学院ID")
    private Long institutionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}