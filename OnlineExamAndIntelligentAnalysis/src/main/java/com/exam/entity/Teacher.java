package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.exam.enums.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName teacher
 */
@Schema(description = "教师表")
@TableName(value ="teacher")
@Data
public class Teacher extends BaseEntity {

    /**
     * 职工号
     */
    @Schema(description = "职工号")
    private Long eno;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 学院ID
     */
    @Schema(description = "学院ID")
    private Long instituteId;

    /**
     * '助教','讲师','副教授','教授':1,2,3,4
     */
    @Schema(description = "职务 1：助教 2：讲师 3：副教授 4：教授")
    private Position position;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}