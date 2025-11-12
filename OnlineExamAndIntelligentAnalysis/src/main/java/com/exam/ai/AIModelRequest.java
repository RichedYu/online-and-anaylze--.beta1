package com.exam.ai;

import java.util.Collections;
import java.util.Map;

/**
 * 模型调用上下文 // Model invocation context
 */
public record AIModelRequest(
        boolean functionCallEnabled, // 是否启用函数调用 // Flag to enable function calling
        String functionName, // 函数名称 // Function name
        Map<String, Object> metadata // 扩展元信息 // Extra metadata
) {

    public AIModelRequest {
        metadata = metadata == null ? Collections.emptyMap() : Collections.unmodifiableMap(metadata); // 确保不可变 // Ensure immutability
    }

    /**
     * 构建普通对话请求 // Build plain chat request
     */
    public static AIModelRequest simple() {
        return new AIModelRequest(false, null, Collections.emptyMap());
    }

    /**
     * 构建函数调用请求 // Build request with Function Calling
     */
    public static AIModelRequest withFunction(String functionName) {
        return new AIModelRequest(true, functionName, Collections.emptyMap());
    }
}
