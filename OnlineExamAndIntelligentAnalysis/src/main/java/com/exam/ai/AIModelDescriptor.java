package com.exam.ai;

/**
 * 模型元信息描述 // Model metadata descriptor
 */
public record AIModelDescriptor(
        String code, // 模型编码 // Model code
        String displayName, // 展示名称 // Display name
        boolean supportsFunction, // 是否支持函数调用 // Supports Function Calling
        boolean healthy, // 健康状态 // Health status
        boolean active // 是否激活 // Active flag
) {
}
