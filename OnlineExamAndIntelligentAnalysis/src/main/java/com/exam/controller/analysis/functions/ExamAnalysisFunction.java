package com.exam.controller.analysis.functions;

import com.exam.dto.ScoreAnalysisDto;
import com.exam.service.ScoreService;
import jakarta.annotation.Resource;

import java.util.function.Function;

/**
 * Author:CM
 * Date: 2025/2/10 21:46
 */

public class ExamAnalysisFunction implements Function<ExamAnalysisFunction.Request, ExamAnalysisFunction.Response> {

    @Resource
    private ScoreService scoreService;

    // 接收GPT提取后的信息，自动调用该方法进行处理
    @Override
    public Response apply(Request request) {

        if(request.scoreId == null){
            return new Response("参数缺失，无法进行分析");
        }
        System.out.println("正在分析该学生的考试信息--------");
        ScoreAnalysisDto scoreAnalysisDto = scoreService.getExamAnalysis(request.scoreId);
        return new Response("获取到该学生的考试信息："+scoreAnalysisDto.toString());
    }

    // 密封类，负责告诉GPT要提取哪些关键信息，接收GPT提取后的信息
    public record Request(Long scoreId){}

    // 最终响应GPT
    public record Response(String message){}

}
