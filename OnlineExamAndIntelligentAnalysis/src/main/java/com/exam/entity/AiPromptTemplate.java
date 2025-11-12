package com.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * AI提示词模板实体类 // AI Prompt Template Entity
 * 用于管理和版本控制提示词模板 // For managing and versioning prompt templates
 * 
 * @author System
 * @date 2025-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "ai_prompt_template", autoResultMap = true)
@Schema(description = "AI提示词模板 // AI Prompt Template")
public class AiPromptTemplate extends BaseEntity {

    @Schema(description = "模板名称 // Template name")
    @TableField("template_name")
    private String templateName;

    @Schema(description = "模板编码(唯一标识) // Template code (unique identifier)")
    @TableField("template_code")
    private String templateCode;

    @Schema(description = "模板内容(支持变量占位符{{variable}}) // Template content (supports {{variable}} placeholders)")
    @TableField("template_content")
    private String templateContent;

    @Schema(description = "分类(EXAM_ANALYSIS/RECOMMENDATION/PREDICTION等) // Category")
    @TableField("category")
    private String category;

    @Schema(description = "变量定义(JSON格式) // Variable definitions (JSON format)")
    @TableField(value = "variables", typeHandler = JacksonTypeHandler.class)
    private Map<String, String> variables;

    @Schema(description = "版本号 // Version number")
    @TableField("version")
    private String version;

    @Schema(description = "模板描述 // Template description")
    @TableField("description")
    private String description;

    @Schema(description = "是否启用(0:禁用 1:启用) // Active status (0:disabled 1:enabled)")
    @TableField("is_active")
    private Boolean isActive;

    @Schema(description = "使用次数统计 // Usage count statistics")
    @TableField("usage_count")
    private Long usageCount;

    @Schema(description = "平均响应时间(ms) // Average response time (ms)")
    @TableField("avg_response_time")
    private Integer avgResponseTime;

    @Schema(description = "最后使用时间 // Last used timestamp")
    @TableField("last_used_at")
    private Date lastUsedAt;
}