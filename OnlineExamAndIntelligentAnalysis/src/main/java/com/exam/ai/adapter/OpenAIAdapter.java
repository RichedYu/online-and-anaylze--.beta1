package com.exam.ai.adapter;

import com.exam.ai.AIModelAdapter;
import com.exam.ai.AIModelRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

/**
 * OpenAI官方模型适配器 // Adapter for OpenAI models via Spring AI
 */
@Service
public class OpenAIAdapter implements AIModelAdapter {

    private final ChatClient chatClient; // Spring AI客户端 // Spring AI chat client

    public OpenAIAdapter(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getName() {
        return "openai"; // 内部编码 // Internal code
    }

    @Override
    public String getDisplayName() {
        return "OpenAI GPT"; // 展示名称 // Display name
    }

    @Override
    public boolean supportsFunctionCalling() {
        return true; // OpenAI原生支持Function Calling // Native support
    }

    @Override
    public boolean isHealthy() {
        return true; // 依赖Spring AI健康探测，此处返回true // Defer to Spring AI health probes
    }

    @Override
    public String chat(String prompt, AIModelRequest request) {
        var promptSpec = chatClient.prompt(); // 构建提示词 // Build prompt
        if (request.functionCallEnabled() && StringUtils.hasText(request.functionName())) {
            promptSpec = promptSpec.functions(request.functionName()); // 绑定函数调用 // Attach function calling
        }
        return promptSpec.user(prompt).call().content(); // 执行同步对话 // Execute sync call
    }

    @Override
    public Flux<String> stream(String prompt, AIModelRequest request) {
        var promptSpec = chatClient.prompt(); // 构建提示词 // Build prompt
        if (request.functionCallEnabled() && StringUtils.hasText(request.functionName())) {
            promptSpec = promptSpec.functions(request.functionName()); // 绑定函数调用 // Attach function calling
        }
        return promptSpec.user(prompt).stream().content(); // 执行流式对话 // Execute streaming call
    }
}
