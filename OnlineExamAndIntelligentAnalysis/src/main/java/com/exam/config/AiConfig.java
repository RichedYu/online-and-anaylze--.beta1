package com.exam.config;

import com.exam.controller.analysis.functions.ExamAnalysisFunction;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

/**
 * Author:CM
 * Date: 2025/2/10 16:51
 */
@Configuration
public class AiConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {

        return builder.defaultSystem("你是一名井冈山大学的教授，教授的研究方向是人工智能。你非常喜欢唱跳、rap、打篮球").
                build();
    }

    @Bean
    @Description("从数据库获取到考试ID为scoreId=xxx的考试信息")
    public Function<ExamAnalysisFunction.Request, ExamAnalysisFunction.Response> examAnalysisFunction() {
        return new ExamAnalysisFunction();
    }

}
