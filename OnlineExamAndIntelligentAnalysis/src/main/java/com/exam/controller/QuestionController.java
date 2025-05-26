package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Subject;
import com.exam.service.QuestionService;
import com.exam.service.SubjectService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.QuestionInfoVo;
import com.exam.vo.QuestionVo;
import com.exam.vo.SubjectInfoVo;
import com.exam.vo.questionType.JudgeVo;
import com.exam.vo.questionType.SingleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Author:CM
 * Date: 2025/1/23 22:26
 */
@Tag(name = "题库管理")
@RestController
@RequestMapping("/exam")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    // 获得题目列表(分页)
    @Operation(summary = "获得题目列表")
    @GetMapping("/questionList/{current}/{size}/{keyword}/{subjectId}/{chapterId}")
    public Result<IPage<QuestionInfoVo>> getQuestionList(@PathVariable("current") Integer current,
                                                       @PathVariable("size") Integer size,
                                                       @PathVariable(value = "keyword",required = false) String keyword,
                                                      @PathVariable(value = "subjectId",required = false) Long subjectId,
                                                      @PathVariable(value = "chapterId",required = false) Long chapterId) {
        System.out.println("正在调用service层方法获得分页数据-----------");
        assert current != null;
        assert size != null;
        System.out.println("subjectId:"+subjectId+"chapterId:"+chapterId);
        // 调用service层方法获得分页数据
        Page<QuestionInfoVo> page = new Page<>(current, size);
        IPage<QuestionInfoVo> questionInfoVos = questionService.getQuestionList(page, keyword,subjectId,chapterId);
        System.out.println("题目列表数据-----------"+questionInfoVos.getRecords().toString());
        return Result.ok(questionInfoVos);
    }

    // 新增题目
    @Operation(summary = "新增题目")
    @PostMapping("/createQuestion")
    public Result<String> createQuestion(@RequestBody QuestionInfoVo questionInfoVo) {
        System.out.println("正在调用service层方法新增题目-");
        // 数据校验
        System.out.println(questionInfoVo.toString());
        if(questionInfoVo.getQuestionContent() == null){
            System.out.println("题目内容不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        if(questionInfoVo.getQuestionScore() == null){
            System.out.println("题目分值不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        if(questionInfoVo.getQuestionType() == null){
            System.out.println("题目类型不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        if(questionInfoVo.getQuestionLevel() == null){
            System.out.println("题目难度不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        if(questionInfoVo.getChapterId() == null){
            System.out.println("章节ID不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
//        // 调用service层方法新增题目
//        boolean flag = questionService.createQuestion(questionInfoVo);
//        if (flag) {
//            System.out.println("新增题目成功-----------正在返回成功信息-");
//            return Result.ok("创建成功");
//        } else {
//            return Result.fail(ResultCodeEnum.FAIL);
//        }
        return null;
    }


    // 删除题目
    @Operation(summary = "删除题目")
    @DeleteMapping("/deleteQuestion/{questionId}")
    public Result<String> deleteSubject(@PathVariable("questionId") Long questionId) {
        System.out.println("正在调用service层方法删除题目-");
        if (questionId == null){
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        boolean flag = questionService.removeById(questionId);
        if (flag) {
            return Result.ok("删除成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 获取错题集
    @Operation(summary = "获取错题集")
    @GetMapping("/getWrongQuestion")
    public Result<IPage<QuestionVo>> getWrongQuestion(@RequestParam("studentId") Long studentId
                                                    ,@RequestParam("subjectId") Long subjectId
                                                    ,@RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size) {
        System.out.println("正在调用service层方法获取错题集-");
        if (studentId == null || subjectId == null){
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        Page<QuestionVo> pageParam = new Page<>(page, size);
        IPage<QuestionVo> questionVos = questionService.getWrongQuestion(pageParam, studentId, subjectId);
        return Result.ok(questionVos);
    }

    // 创建单选题
    @Operation(summary = "创建单选题")
    @PostMapping("/createSingle")
    public Result createSingle(@RequestBody SingleVo singleVo) {
        System.out.println("正在调用service层方法创建单选题-"+singleVo.toString());
        if (singleVo.getSubjectId() == null || singleVo.getChapterId() == null || singleVo.getQuestion() == null || singleVo.getAnalysis() == null || singleVo.getOptionA() == null || singleVo.getOptionB() == null || singleVo.getOptionC() == null || singleVo.getOptionD() == null || singleVo.getCorrectAnswers() == null || singleVo.getLevel() == null){
            System.out.println("参数缺失-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }

        boolean flag = questionService.createSingle(singleVo);
        if (flag) {
            System.out.println("创建成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            System.out.println("创建失败-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 创建多选题
    @Operation(summary = "创建多选题")
    @PostMapping("/createMulti")
    public Result createMulti(@RequestBody SingleVo singleVo) {
        System.out.println("正在调用service层方法创建单选题-"+singleVo.toString());
        if (singleVo.getSubjectId() == null || singleVo.getChapterId() == null || singleVo.getQuestion() == null || singleVo.getAnalysis() == null || singleVo.getOptionA() == null || singleVo.getOptionB() == null || singleVo.getOptionC() == null || singleVo.getOptionD() == null || singleVo.getCorrectAnswers() == null || singleVo.getLevel() == null){
            System.out.println("参数缺失-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }

        boolean flag = questionService.createMulti(singleVo);
        if (flag) {
            System.out.println("创建成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            System.out.println("创建失败-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 创建判断题
    @Operation(summary = "创建判断题")
    @PostMapping("/createJudge")
    public Result createJudge(@RequestBody JudgeVo judgeVo) {
        System.out.println("正在调用service层方法创建判断题-"+judgeVo.toString());
        if (judgeVo.getSubjectId() == null || judgeVo.getChapterId() == null || judgeVo.getQuestion() == null || judgeVo.getAnalysis() == null || judgeVo.getCorrectAnswers() == null || judgeVo.getLevel() == null){
            System.out.println("参数缺失-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }

        boolean flag = questionService.createJudge(judgeVo);
        if (flag) {
            System.out.println("创建成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            System.out.println("创建失败-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 创建填空题
    @Operation(summary = "创建填空题")
    @PostMapping("/createFill")
    public Result createFill(@RequestBody JudgeVo judgeVo) {
        System.out.println("正在调用service层方法创建填空题-"+judgeVo.toString());
        if (judgeVo.getSubjectId() == null || judgeVo.getChapterId() == null || judgeVo.getQuestion() == null || judgeVo.getAnalysis() == null || judgeVo.getCorrectAnswers() == null || judgeVo.getLevel() == null){
            System.out.println("参数缺失-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }

        boolean flag = questionService.createFill(judgeVo);
        if (flag) {
            System.out.println("创建成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            System.out.println("创建失败-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 创建简答题
    @Operation(summary = "创建简答题")
    @PostMapping("/createShortAnswer")
    public Result createShortAnswer(@RequestBody JudgeVo judgeVo) {
        System.out.println("正在调用service层方法创建简答题-"+judgeVo.toString());
        if (judgeVo.getSubjectId() == null || judgeVo.getChapterId() == null || judgeVo.getQuestion() == null || judgeVo.getAnalysis() == null || judgeVo.getCorrectAnswers() == null || judgeVo.getLevel() == null){
            System.out.println("参数缺失-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        boolean flag = questionService.createShortAnswer(judgeVo);
        if (flag) {
            System.out.println("创建成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            System.out.println("创建失败-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 创建应用题
    @Operation(summary = "创建应用题")
    @PostMapping("/createApplication")
    public Result createApplication(@RequestBody JudgeVo judgeVo) {
        System.out.println("正在调用service层方法创建应用题-"+judgeVo.toString());
        if (judgeVo.getSubjectId() == null || judgeVo.getChapterId() == null || judgeVo.getQuestion() == null || judgeVo.getAnalysis() == null || judgeVo.getCorrectAnswers() == null || judgeVo.getLevel() == null){
            System.out.println("参数缺失-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        boolean flag = questionService.createApplication(judgeVo);
        if (flag) {
            System.out.println("创建成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            System.out.println("创建失败-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }




}
