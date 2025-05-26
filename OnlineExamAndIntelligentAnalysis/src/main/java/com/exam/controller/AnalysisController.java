package com.exam.controller;

import com.exam.service.AnalysisService;
import com.exam.utils.result.Result;
import com.exam.vo.KnowledgePointsAnalysisVo;
import com.exam.vo.LeiDaTuVo;
import com.exam.vo.QuestionTypeAnalysisVo;
import com.exam.vo.SubjectDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;

/**
 * Author:CM
 * Date: 2025/1/17 10:40
 */
@Tag(name = "智能分析模块")
@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Resource
    private AnalysisService analysisService;

    // 根据学号获取成绩表中的考过的考试科目
    @Operation(summary = "根据学号获取成绩表中的考过的考试科目")
    @GetMapping("/getSubjects/{studentId}")
    public Result<List<HashMap<String, Object>>> getSubjects(@PathVariable("studentId") String studentId) {
        // 调用业务层方法获取数据
        assert studentId != null;
        List<HashMap<String, Object>> subjects = analysisService.getSubjects(studentId);
        // 返回数据
        return Result.ok(subjects);
    }

    // 根据studentId和subjectId获取该科目考试的详细信息
    @Operation(summary = "根据subjectId获取该科目考试的详细信息")
    @GetMapping("/getSubjectDetail/{studentId}/{subjectId}")
    public Result<SubjectDetailVo> getSubjectDetail(@PathVariable("studentId") Long studentId,
                                                    @PathVariable("subjectId") Long subjectId) {
        // 调用业务层方法获取数据
        assert subjectId != null;
        SubjectDetailVo subjectDetailVo = analysisService.getSubjectDetail(studentId, subjectId);
        // 返回数据
        return Result.ok(subjectDetailVo);
    }

    // 根据studentId和subjectId获取班级评分等级分布情况
    @Operation(summary = "根据studentId和subjectId获取班级评分等级分布情况")
    @GetMapping("/grade/{studentId}/{subjectId}")
    public Result<List<HashMap<String, Object>>> grade(@PathVariable("studentId") Long studentId,
                                                       @PathVariable("subjectId") Long subjectId) {
        // 调用业务层方法获取数据
        assert studentId != null;
        assert subjectId != null;
        List<HashMap<String, Object>> grade = analysisService.getGrade(studentId, subjectId);
        // 返回数据
        return Result.ok(grade);
    }
    // 根据studentId和subjectId获取自己在班级中所处水平
    @Operation(summary = "根据studentId和subjectId获取班级评分等级分布情况")
    @GetMapping("/selfGrade/{studentId}/{subjectId}")
    public Result<HashMap<String, Object>> getSelfGrade(@PathVariable("studentId") Long studentId,
                                                       @PathVariable("subjectId") Long subjectId) {
        // 调用业务层方法获取数据
        assert studentId != null;
        assert subjectId != null;
        HashMap<String, Object> selfGrade = analysisService.getSelfGrade(studentId, subjectId);
        // 返回数据
        System.out.println("selfGrade = ==========================================");
        return Result.ok(selfGrade);
    }

    // 根据studentId和subjectId获取该科目近六次考试的班级平均分和个人分数
    @Operation(summary = "根据studentId和subjectId获取该科目近六次考试的班级平均分和个人分数")
    @GetMapping("/trend/{studentId}/{subjectId}")
    public Result<HashMap<String, List<Object>>> trend(@PathVariable("studentId") Long studentId,
                                                       @PathVariable("subjectId") Long subjectId) {
        // 调用业务层方法获取数据
        assert studentId != null;
        assert subjectId != null;
        HashMap<String, List<Object>> trend = analysisService.getTrend(studentId, subjectId);
        // 返回数据
        return Result.ok(trend);
    }

    // 根据studentId和subjectId获取该科目考试各题型的正确率
    @Operation(summary = "根据studentId和subjectId获取该科目考试各题型的正确率")
    @GetMapping("/correctRate/{studentId}/{subjectId}")
    public Result<HashMap<String, Double>> correctRate(@PathVariable("studentId") Long studentId,
                                                       @PathVariable("subjectId") Long subjectId) {
        // 调用业务层方法获取数据
        assert studentId != null;
        assert subjectId != null;
        HashMap<String, Double> correctRate = analysisService.getCorrectRate(studentId, subjectId);
        // 返回数据
        return Result.ok(correctRate);
    }

    // 根据studentId和subjectId获取该科目章节各难度（1：容易，2：中等，3：困难）的正确率
    @Operation(summary = "根据studentId和subjectId获取该科目章节各难度（1：容易，2：中等，3：困难）的正确率")
    @GetMapping("/correctRateByDifficulty/{studentId}/{subjectId}")
    public Result<HashMap<String, Object>> correctRateByDifficulty(@PathVariable("studentId") Long studentId,
                                                       @PathVariable("subjectId") Long subjectId) {
        // 调用业务层方法获取数据
        assert studentId != null;
        assert subjectId != null;
        HashMap<String, Object> correctRate = analysisService.getCorrectRateByDifficulty(studentId, subjectId);
        // 返回数据
        return Result.ok(correctRate);
    }

    // 根据scoreId获取各题型得分情况
    @Operation(summary = "根据scoreId获取各题型得分情况")
    @GetMapping("/getExamQuestionTypeAnalysis/{scoreId}")
    public Result<List<QuestionTypeAnalysisVo>> getExamQuestionTypeAnalysis(@PathVariable("scoreId") Long scoreId) {
        // 调用业务层方法获取数据
        assert scoreId != null;
        List<QuestionTypeAnalysisVo> questionTypeAnalysisVos = analysisService.getExamQuestionTypeAnalysis(scoreId);
        // 返回数据
        return Result.ok(questionTypeAnalysisVos);
    }

    // 根据scoreId获取考试题目难度分布情况
    @Operation(summary = "根据scoreId获取考试题目难度分布情况")
    @GetMapping("/getExamQuestionDifficulty/{scoreId}")
    public Result<List<QuestionTypeAnalysisVo>> getExamQuestionDifficulty(@PathVariable("scoreId") Long scoreId) {
        // 调用业务层方法获取数据
        assert scoreId != null;
        List<QuestionTypeAnalysisVo> questionTypeAnalysisVos = analysisService.getExamQuestionDifficulty(scoreId);
        // 返回数据
        return Result.ok(questionTypeAnalysisVos);
    }

    // 根据scoreId获取各试题知识点所属章节数
    @Operation(summary = "根据scoreId获取各试题知识点所属章节数")
    @GetMapping("/getExamQuestionChapter/{scoreId}")
    public Result<List<HashMap<String, Object>>> getExamQuestionChapter(@PathVariable("scoreId") Long scoreId) {
        // 调用业务层方法获取数据
        assert scoreId != null;
        List<HashMap<String, Object>> examQuestionChapter = analysisService.getExamQuestionChapter(scoreId);
        // 返回数据
        return Result.ok(examQuestionChapter);
    }

    // 根据scoreId获取各知识点所属章节得分率
    @Operation(summary = "根据scoreId获取各知识点所属章节得分率")
    @GetMapping("/getExamQuestionChapterScoreRate/{scoreId}")
    public Result<LeiDaTuVo> getExamQuestionChapterScoreRate(@PathVariable("scoreId") Long scoreId) {
        // 调用业务层方法获取数据
        assert scoreId != null;
        LeiDaTuVo leiDaTuVo = analysisService.getExamQuestionChapterScoreRate(scoreId);
        // 返回数据
        return Result.ok(leiDaTuVo);
    }

    // 根据subjectId获取该科目考试各个知识点的数据
    @Operation(summary = "根据subjectId获取该科目考试各个知识点的数据")
    @GetMapping("/getSubjectKnowledgePoint/{subjectId}/{studentId}")
    public Result<List<KnowledgePointsAnalysisVo>> getSubjectKnowledgePoint(@PathVariable("subjectId") Long subjectId
                                                                      ,@PathVariable("studentId") Long studentId) {
        // 调用业务层方法获取数据
        assert subjectId != null;
        List<KnowledgePointsAnalysisVo> knowledgePointsAnalysisVos = analysisService.getSubjectKnowledgePoint(subjectId, studentId);
        // 返回数据
        return Result.ok(knowledgePointsAnalysisVos);
    }

    // 根据subjectId和studentId获取该学生在该科目考试中薄弱知识点
    @Operation(summary = "根据subjectId和studentId获取该学生在该科目考试中薄弱知识点")
    @GetMapping("/getWeakSubjectKnowledgePoint/{subjectId}/{studentId}")
    public Result<List<KnowledgePointsAnalysisVo>> getWeakSubjectKnowledgePoint(@PathVariable("subjectId") Long subjectId,
                                                                                @PathVariable("studentId") Long studentId) {
        // 调用业务层方法获取数据
        assert subjectId != null;
        List<KnowledgePointsAnalysisVo> knowledgePointsAnalysisVos = analysisService.getWeakSubjectKnowledgePoint(subjectId, studentId);
        // 返回数据
        return Result.ok(knowledgePointsAnalysisVos);
    }

    // 根据subjectId和classId获取班级薄弱知识点
    @Operation(summary = "根据subjectId和classId获取班级薄弱知识点")
    @GetMapping("/getClassWeakSubjectKnowledgePoint/{subjectId}/{classId}")
    public Result<List<KnowledgePointsAnalysisVo>> getClassWeakSubjectKnowledgePoint(@PathVariable("subjectId") Long subjectId,
                                                                                   @PathVariable("classId") Long classId) {
        // 调用业务层方法获取数据
        assert subjectId != null;
        List<KnowledgePointsAnalysisVo> knowledgePointsAnalysisVos = analysisService.getClassWeakSubjectKnowledgePoint(subjectId, classId);
        // 返回数据
        return Result.ok(knowledgePointsAnalysisVos);
    }

}
