package com.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * 提示词模板VO // Prompt Template View Object
 * 用于前端展示和交互 // For frontend display and interaction
 * 
 * @author System
 * @date 2025-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "提示词模板VO // Prompt Template VO")
public class PromptTemplateVo {

    @Schema(description = "模板ID // Template ID")
    private Long id;

    @Schema(description = "模板名称 // Template name")
    private String templateName;

    @Schema(description = "模板编码 // Template code")
    private String templateCode;

    @Schema(description = "模板内容 // Template content")
    private String templateContent;

    @Schema(description = "分类 // Category")
    private String category;

    @Schema(description = "变量定义 // Variable definitions")
    private Map<String, String> variables;

    @Schema(description = "版本号 // Version")
    private String version;

    @Schema(description = "描述 // Description")
    private String description;

    @Schema(description = "是否启用 // Is active")
    private Boolean isActive;

    @Schema(description = "使用次数 // Usage count")
    private Long usageCount;

    @Schema(description = "平均响应时间(ms) // Average response time")
    private Integer avgResponseTime;

    @Schema(description = "最后使用时间 // Last used at")
    private Date lastUsedAt;

    @Schema(description = "创建时间 // Create time")
    private Date createTime;
}