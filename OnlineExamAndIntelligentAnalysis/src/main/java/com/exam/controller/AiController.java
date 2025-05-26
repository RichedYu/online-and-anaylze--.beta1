package com.exam.controller;

import com.exam.dto.ScoreAnalysisDto;
import com.exam.service.AnalysisService;
import com.exam.utils.result.Result;
import com.exam.vo.KnowledgePointsAnalysisVo;
import com.exam.vo.LeiDaTuVo;
import com.exam.vo.QuestionTypeAnalysisVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;

/**
 * Author:CM
 * Date: 2025/2/10 16:21
 */
@Tag(name="AI智能分析模块")
@RestController
@RequestMapping("/login")
public class AiController {

    @Resource
    private ChatClient chatClient;
    @Resource
    private ChatModel chatModel;
    @Resource
    private OpenAiImageModel openAiImageModel;
    @Resource
    private AnalysisService analysisService;

    @Operation(summary = "ai对话")
    @GetMapping(value = "/ai",produces = "text/html;charset=UTF-8")
    public String generation(String userInput) {
        System.out.println("正在调用AI智能分析模块对话功能--------");
        return this.chatClient.prompt()// 提示词
                .functions("examAnalysisFunction")
                .user(userInput)// 用户输入
                .call()// 远程调用
                .content();// 返回string类型文本
    }

    @Operation(summary = "以流的方式回答")
    @PostMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream() {
        System.out.println("正在调用AI智能分析模块流式对话功能--------");
        String userInput = "请用两百字介绍自己";
        Flux<String> output = this.chatClient.prompt()// 提示词
                .functions("examAnalysisFunction")
                .user(userInput)// 用户输入
                .stream()// 以流的方式回答
                .content();// 返回string类型文本
        return output;
    }

    @Operation(summary = "考试分析1")
    @PostMapping(value = "/examAnalysis1")
    public Result<HashMap<String, Object>> examAnalysis1(@RequestBody List<QuestionTypeAnalysisVo> questionTypeAnalysisVoList) {
        System.out.println("正在调用AI智能分析模块对话功能--------");
        String userInput = "通过" + questionTypeAnalysisVoList.toString() + "分析这次考试的成绩，用250字的篇幅总结：直接说优缺点，该如何做，不要重述我的数据，用'你'称呼学生" +
                "以本次考试中，你在为开头回答";
        String output =  this.chatClient.prompt()// 提示词
                .user(userInput)// 用户输入
                .call()// 远程调用
                .content();// 返回string类型文本
        HashMap<String, Object> result = new HashMap<>();
        result.put("message", output);
        return Result.ok(result);
    }

    @Operation(summary = "考试分析2")
    @PostMapping(value = "/examAnalysis2")
    public Result<HashMap<String, Object>> examAnalysis2(@RequestBody List<QuestionTypeAnalysisVo> questionTypeAnalysisVoList) {
        System.out.println("正在调用AI智能分析模块对话功能--------");
        String userInput = "通过" + questionTypeAnalysisVoList.toString() + "分析这次考试难易程度及本次考试的成绩及建议，用250字的篇幅总结：直接说优缺点，该如何做，不要重述我的数据，用'你'称呼学生" +
                "以本次考试中，你在为开头回答";
        String output =  this.chatClient.prompt()// 提示词
                .user(userInput)// 用户输入
                .call()// 远程调用
                .content();// 返回string类型文本
        HashMap<String, Object> result = new HashMap<>();
        result.put("message", output);
        return Result.ok(result);
    }

    @Operation(summary = "考试分析3")
    @PostMapping(value = "/examAnalysis3")
    public Result<HashMap<String, Object>> examAnalysis3(@RequestBody List<KnowledgePointsAnalysisVo> knowledgePointsAnalysisVos) {
        System.out.println("正在调用AI智能分析模块对话功能--------");
        String userInput = "通过" + knowledgePointsAnalysisVos.toString() + "分析学生的知识点掌握情况，用400字的篇幅总结：直接说优缺点，薄弱知识点，该如何做，不要重述我的数据，用'你'称呼学生" +
                "以本科目中，你在为开头回答";
        String output =  this.chatClient.prompt()// 提示词
                .user(userInput)// 用户输入
                .call()// 远程调用
                .content();// 返回string类型文本
        HashMap<String, Object> result = new HashMap<>();
        result.put("message", output);
        return Result.ok(result);
    }

    @Operation(summary = "考试分析4")
    @GetMapping(value = "/examAnalysis4/{scoreId}")
    public Result<HashMap<String, Object>> examAnalysis4(@PathVariable("scoreId") Long scoreId) {
        System.out.println("正在调用AI智能分析模块对话功能--------");
        LeiDaTuVo scoreRate = analysisService.getExamQuestionChapterScoreRate(scoreId);
        List<HashMap<String, Object>> num = analysisService.getExamQuestionChapter(scoreId);
        String userInput = "通过本次考试试题所属章节占比：" + num.toString() +",和试题所属章节得分率："+scoreRate.toString() + "分析学生的考试情况，用250字的篇幅总结：直接说优缺点，该如何做，不要重述我的数据，用'你'称呼学生" +
                "以本次考试中，你在为开头回答";
        String output =  this.chatClient.prompt()// 提示词
                .user(userInput)// 用户输入
                .call()// 远程调用
                .content();// 返回string类型文本
        HashMap<String, Object> result = new HashMap<>();
        result.put("message", output);
        return Result.ok(result);
    }

//    // 获取实时信息回答问题
//    @Operation(summary = "获取实时信息回答问题")
//    @GetMapping("/function")
//    public Flux<ChatResponse> function(@RequestParam(value = "userInput",
//            defaultValue = "分析学生ID为25010002的考试成绩，并分析成绩趋势，进步情况")
//                                                   String userInput) {
//        System.out.println("正在调用AI智能分析模块获取实时信息回答问题功能--------");
//        OpenAiChatOptions options = OpenAiChatOptions.builder()
//                .function("locationNameFunction")
//                .model("gpt-4-turbo")
//                .build();
//        Flux<ChatResponse> response = chatModel.stream(new Prompt(userInput, options));
//        return response;
//    }


    // 文生图
//    @Operation(summary = "生成文生图")
//    @GetMapping(value = "/generate")
//    public String generate(String userInput) {
//        System.out.println("正在调用AI智能分析模块生成文生图功能--------");
//        ImageResponse response = openAiImageModel.call(
//                new ImagePrompt(userInput,
//                        OpenAiImageOptions.builder()
//                               .withQuality("hd")
//                               .withModel(OpenAiImageApi.DEFAULT_IMAGE_MODEL)
//                               .withN(1)
//                               .withHeight(1024)
//                               .withWidth(1024)
//                               .build())
//        );
//        return response.getResult().getOutput().getUrl();
//    }

}
