package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName major
 */
@Schema(description = "专业表")
@TableName(value ="major")
@Data
public class Major extends BaseEntity {

    /**
     * 
     */
    @Schema(description = "专业名称")
    private String name;

    /**
     * 
     */
    @Schema(description = "学院ID")
    private Long institutionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}