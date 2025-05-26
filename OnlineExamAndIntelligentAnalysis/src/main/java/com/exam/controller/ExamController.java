package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exam.entity.Answers;
import com.exam.entity.MultiChoice;
import com.exam.entity.Question;
import com.exam.service.AnswersService;
import com.exam.service.MultiChoiceService;
import com.exam.service.PaperService;
import com.exam.service.QuestionService;
import com.exam.utils.result.Result;
import com.exam.vo.CreateExamVo;
import com.exam.vo.ExamInfoVo;
import com.exam.vo.MultiChoiceVo;
import com.exam.vo.QuestionVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * Author:CM
 * Date: 2025/2/5 20:08
 */
@Tag(name = "考试管理")
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Resource
    private PaperService paperService;
    @Resource
    private QuestionService questionService;
    @Resource
    private MultiChoiceService multiChoiceService;
    @Resource
    private AnswersService answersService;

    @Operation(summary = "根据examId获取试卷的各试题详情")
    @GetMapping("/getExamInfo/{examId}")
    public Result<ExamInfoVo> getExamInfo(@PathVariable("examId") Long examId) {
        ExamInfoVo examInfoVo = paperService.getExamInfo(examId);
        return Result.ok(examInfoVo);
    }

    @Operation(summary = "根据questionId获取试题详情")
    @GetMapping("/getQuestion/{questionId}")
    public Result<Object> getQuestion(@PathVariable("questionId") Long questionId) {
        Question question = questionService.getById(questionId);
        String code = question.getType().getCode();
        QuestionVo questionVo = new QuestionVo();
        questionVo.setQuestionId(questionId);
        questionVo.setQuestion(question.getQuestion());
        questionVo.setScore(question.getScoreValue());
        questionVo.setType(code);
        questionVo.setAnalysis(question.getAnalysis());

        if(code.equals("1") || code.equals("2")){
            LambdaQueryWrapper<MultiChoice> wrapper = new LambdaQueryWrapper<MultiChoice>();
            wrapper.eq(MultiChoice::getQuestionId, questionId);
            MultiChoice multiChoice = multiChoiceService.getOne(wrapper);
            MultiChoiceVo multiChoiceVo = new MultiChoiceVo();
            multiChoiceVo.setType(code);
            multiChoiceVo.setQuestionId(questionId);
            multiChoiceVo.setQuestion(question.getQuestion());
            multiChoiceVo.setOptionA(multiChoice.getOptiona());
            multiChoiceVo.setOptionB(multiChoice.getOptionb());
            multiChoiceVo.setOptionC(multiChoice.getOptionc());
            multiChoiceVo.setOptionD(multiChoice.getOptiond());
            multiChoiceVo.setAnswer(multiChoice.getCorrectAnswer().getCode());
            multiChoiceVo.setScore(question.getScoreValue());
            multiChoiceVo.setAnalysis(question.getAnalysis());
            return Result.ok(multiChoiceVo);
        }else if(code.equals("3")){
            return Result.ok(questionVo);
        }else if(code.equals("4")){
            LambdaQueryWrapper<Answers> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Answers::getQuestionId, questionId);
            Answers answer = answersService.getOne(wrapper);
            String[] split = answer.getAnswer().split(",");
            questionVo.setAnswerCount(split.length);
            return Result.ok(questionVo);
        }else if(code.equals("5")){
            return Result.ok(questionVo);
        }else if(code.equals("6")){
            return Result.ok(questionVo);
        }

        return Result.fail();
    }

    // 发布考试接口
    @Operation(summary = "发布考试")
    @PostMapping("/createExam")
    public Result createExam(@RequestBody CreateExamVo createExamVo) {
        paperService.createExam(createExamVo);
        return Result.ok();
    }


}
