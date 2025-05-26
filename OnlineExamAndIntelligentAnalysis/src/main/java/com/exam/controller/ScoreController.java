package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import com.exam.entity.ScoreDetail;
import com.exam.enums.ReviewEnum;
import com.exam.exception.ExamException;
import com.exam.service.PaperQuestionService;
import com.exam.service.ScoreDetailService;
import com.exam.service.ScoreService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Author:CM
 * Date: 2024/12/24 18:57
 */
@Tag(name = "分数管理")
@RestController
@RequestMapping("/exam")
public class ScoreController {

    @Resource
    private ScoreService scoreService;
    @Resource
    private PaperQuestionService paperQuestionService;
    @Resource
    private ScoreDetailService scoreDetailService;

    @Operation(summary = "分页查询所有分数记录")
    @GetMapping("/score")
    public Result<List<Score>> findAll(){
        System.out.println("查询所有分数");
        LambdaQueryWrapper<Score> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Score::getReview, ReviewEnum.REVIEWED);
        return Result.ok(scoreService.list(wrapper));
    }

    @Operation(summary = "根据学号分页查询分数记录")
    @GetMapping("/score/{page}/{size}/{studentId}")
    public Result<IPage<ScoreVo>> findById(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("studentId") Integer studentId) {
        Page<Score> scorePage = new Page<>(page, size);
        IPage<ScoreVo> scoreVoIPage = scoreService.findById(scorePage, studentId);
//        if(scoreList.isEmpty()){
//            throw new ExamException(ResultCodeEnum.NOT_FOUND_INFO);
//        }
        return Result.ok(scoreVoIPage);
    }

    @Operation(summary = "根据学号查询分数记录")
    @GetMapping("/score/{studentId}")
    public Result<List<ScoreVo>> findById(@PathVariable("studentId") Integer studentId) {
        return Result.ok(scoreService.findById(studentId));
    }

    @Operation(summary = "查询学生是否已参加过该考试")
    @GetMapping("/queryScore/{studentId}/{paperId}")
    public Result queryScore(@PathVariable("studentId") Long studentId,@PathVariable("paperId") Long paperId){
        Score score = scoreService.queryScore(studentId, paperId);
        if(score != null){
            return Result.fail(ResultCodeEnum.INSERT_ERROR);
        }
        return Result.ok();
    }

    // 保存考试成绩及结果
    @Operation(summary = "保存考试成绩及结果")
    @PostMapping("/saveScore")
    public Result saveScore(@RequestBody ScoreResultVo scoreResultVo){
        System.out.println(scoreResultVo);
        // 通过paperId 查询试题总数，判断用户填写答案是否完整
        int num = paperQuestionService.queryQuestionNum(scoreResultVo.getPaperId());
        if(scoreResultVo.getAnswer().size() != num){
            System.out.println("答案不完整------------");
            return Result.fail(ResultCodeEnum.DATA_ERROR);
        }
        // 完整则保存到数据库
        boolean is_success = scoreService.saveScore(scoreResultVo);
        if(!is_success){
            return Result.fail();
        }
        return Result.ok("保存成功");
    }

    @Operation(summary = "查询成绩详情")
    @GetMapping("/scoreRecord/{scoreId}")
    public Result<ScoreRecordVo> getScoreRecord(@PathVariable("scoreId") Long scoreId){
        ScoreRecordVo scoreRecordVo = scoreService.getScoreRecord(scoreId);
        System.out.println(scoreRecordVo);
        return Result.ok(scoreRecordVo);
    }

    // 创建成绩单，保存考试记录
    @Operation(summary = "创建成绩单，保存考试记录")
    @PostMapping("/createScoreRecord")
    public Result createScoreRecord(@RequestBody ScoreResultVo scoreResultVo){
        // 保存考试记录
        System.out.println(scoreResultVo.toString());
        if(scoreService.saveScoreRecord(scoreResultVo)){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    // 试卷批改列表
    @Operation(summary = "试卷批改列表")
    @GetMapping("/reviewList/{current}/{size}/{keyword}/{paperId}/{classId}")
    public Result<IPage<MarkingPaperVo>> reviewList(@PathVariable("current") Integer current,
                                                    @PathVariable("size") Integer size,
                                                    @PathVariable(value = "keyword", required = false) String keyword,
                                                    @PathVariable(value = "paperId", required = false) Long paperId,
                                                    @PathVariable(value = "classId", required = false) Long classId){
        assert keyword != null || paperId != null || classId != null;
        Page<Score> scorePage = new Page<>(current, size);
        IPage<MarkingPaperVo> result = scoreService.reviewList(scorePage, keyword, paperId, classId);
        System.out.println("获取批改列表成功");
        return Result.ok(result);
    }

    // 试卷批改列表
    @Operation(summary = "试卷批改列表")
    @GetMapping("/reviewed/{current}/{size}/{keyword}/{paperId}/{classId}")
    public Result<IPage<MarkingPaperVo>> reviewed(@PathVariable("current") Integer current,
                                                    @PathVariable("size") Integer size,
                                                    @PathVariable(value = "keyword", required = false) String keyword,
                                                    @PathVariable(value = "paperId", required = false) Long paperId,
                                                    @PathVariable(value = "classId", required = false) Long classId){
        assert keyword != null || paperId != null || classId != null;
        Page<Score> scorePage = new Page<>(current, size);
        IPage<MarkingPaperVo> result = scoreService.reviewed(scorePage, keyword, paperId, classId);
        System.out.println("获取已批改列表成功");
        return Result.ok(result);
    }

    // 根据scoreId查找用户答案
    @Operation(summary = "根据scoreId查找用户答案")
    @GetMapping("/getAnswer/{scoreId}")
    public Result<List<Answer>> getAnswer(@PathVariable("scoreId") Long scoreId){
        System.out.println("正在获取用户答案--------");
        List<Answer> answerList = scoreService.getAnswer(scoreId);
        return Result.ok(answerList);
    }

    // 保存最终得分
    @Operation(summary = "保存最终得分")
    @PostMapping("/saveFinalScore")
    public Result saveFinalScore(@RequestBody FinalScoreVo finalScoreVo){
        System.out.println("正在保存最终得分--------");
        System.out.println(finalScoreVo.toString());
        boolean is_success = scoreService.saveFinalScore(finalScoreVo);
        if(!is_success){
            return Result.fail();
        }
        return Result.ok();
    }

    // 根据studentId,subjectId查找成绩信息
    @Operation(summary = "根据studentId,subjectId查找成绩信息")
    @GetMapping("/queryScoreByStudentIdAndSubjectId/{studentId}/{subjectId}")
    public Result<List<HashMap<String, Object>>> queryScoreByStudentIdAndSubjectId(@PathVariable("studentId") Long studentId, @PathVariable("subjectId") Long subjectId){
            List<HashMap<String, Object>> result = scoreService.queryScoreByStudentIdAndSubjectId(studentId, subjectId);
            return Result.ok(result);
    }

}
