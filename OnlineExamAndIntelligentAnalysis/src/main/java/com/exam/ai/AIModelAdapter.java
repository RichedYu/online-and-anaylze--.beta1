package com.exam.ai;

import reactor.core.publisher.Flux;

/**
 * 多模型统一适配接口 // Unified adapter interface for multi-model support
 */
public interface AIModelAdapter {

    /**
     * 模型唯一编码 // Unique internal model code
     */
    String getName();

    /**
     * 前端展示名称 // Human readable display name
     */
    String getDisplayName();

    /**
     * 是否支持Function Calling // Whether Function Calling is supported
     */
    boolean supportsFunctionCalling();

    /**
     * 模型健康状态 // Model health status
     */
    boolean isHealthy();

    /**
     * 同步对话能力 // Synchronous conversation capability
     */
    String chat(String prompt, AIModelRequest request);

    /**
     * 流式对话能力 // Streaming conversation capability
     */
    Flux<String> stream(String prompt, AIModelRequest request);
}
