package com.exam.ai.adapter;

import com.exam.ai.AIModelAdapter;
import com.exam.ai.AIModelRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * 通义千问占位适配器，便于后续无缝接入 // Placeholder adapter for Qwen to ease future plug-in
 */
@Service
public class QwenMockAdapter implements AIModelAdapter {

    @Override
    public String getName() {
        return "qwen-sandbox"; // 内部编码 // Internal code
    }

    @Override
    public String getDisplayName() {
        return "Qwen Mock"; // 展示名称 // Display name
    }

    @Override
    public boolean supportsFunctionCalling() {
        return false; // 当前占位实现不支持Function Calling // Mock implementation does not support Function Calling
    }

    @Override
    public boolean isHealthy() {
        return true; // 占位实现恒定可用 // Always healthy for placeholder
    }

    @Override
    public String chat(String prompt, AIModelRequest request) {
        return "【Qwen Mock】" + prompt + " -> 请在接入真实模型后替换该实现 / Replace with real model when ready.";
    }

    @Override
    public Flux<String> stream(String prompt, AIModelRequest request) {
        String message = chat(prompt, request);
        return Flux.just(message)
                .delayElements(Duration.ofMillis(50)); // 模拟延迟 // Simulate slight latency
    }
}
