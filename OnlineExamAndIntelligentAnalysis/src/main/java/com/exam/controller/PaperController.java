package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Paper;
import com.exam.service.PaperService;
import com.exam.utils.result.Result;
import com.exam.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

/**
 * Author:CM
 * Date: 2024/12/23 10:31
 */
@Tag(name = "试卷管理")
@RestController
@RequestMapping("/exam")
public class PaperController {

    @Resource
    private PaperService paperService;

    @Operation(summary = "获取所有试卷")
    @GetMapping("/papers")
    public Result<List<PaperVo>> findAll() {
        List<PaperVo> papers = paperService.papers();
        return Result.ok(papers);
    }

    @Operation(summary = "分页获取试卷")
    @GetMapping("/papers/{page}/{size}")
    public Result<IPage<PaperVo>> findAll(@PathVariable("page") Integer page,
                                          @PathVariable("size") Integer size){
        System.out.println("分页查询所有试卷");
        Page<PaperVo> paperVoPage =  new Page<>(page, size);
        IPage<PaperVo> papers = paperService.papers(paperVoPage);
        return Result.ok(papers);
    }

    @Operation(summary = "按时间分类试卷")
    @GetMapping("/sortByTime")
    public Result<SortByTimePapersVo> sortByTime(){
        SortByTimePapersVo sortByTime = paperService.sortByTime();
        return Result.ok(sortByTime);
    }

    @Operation(summary = "按学号分页获取试卷")
    @GetMapping("/papers/{studentId}/{page}/{size}/{key}/{time}")
    public Result<IPage<PaperVo>> findAllByStudentId(@PathVariable("studentId") Long studentId,
                                                    @PathVariable("page") Integer page,
                                                    @PathVariable("size") Integer size,
                                                     @PathVariable("key") String key,
                                                      @PathVariable(value = "time", required = false) String time){
        System.out.println("按学号分页查询所有试卷");
        if(key == null || key.equals("null")){
            key = "";
        }
        Page<PaperVo> paperVoPage =  new Page<>(page, size);
        IPage<PaperVo> papers = paperService.getPapersByStudentId(paperVoPage,studentId,key,time);
        return Result.ok(papers);
    }

    @Operation(summary = "按学号获取试卷")
    @GetMapping("/papers/{studentId}/{key}")
    public Result<List<PaperVo>> findAllByStudentId(@PathVariable("studentId") Long studentId,
                                                    @PathVariable("key") String key){
        System.out.println("按学号查询所有试卷");
        List<PaperVo> papers = paperService.getPapersByStudentId(studentId,key);
        return Result.ok(papers);
    }

    @Operation(summary = "按学号和考试时间分类试卷")
    @GetMapping("/sortByTime/{studentId}")
    public Result<SortByTimePapersVo> sortByTimeAndStudentId(@PathVariable("studentId") Long studentId){
        System.out.println("按学号和考试时间分类试卷");
        SortByTimePapersVo sortByTime = paperService.sortByTime(studentId);
        return Result.ok(sortByTime);
    }

    @Operation(summary = "获取试卷详情")
    @GetMapping("/paperDetail/{examCode}")
    public Result<PaperDetailVo> getPaperDetail(@PathVariable("examCode") Long paperId){
        PaperDetailVo paperDetailVo = paperService.getPaperDetailById(paperId);
        return Result.ok(paperDetailVo);
    }

    @Operation(summary = "获取试题详情")
    @GetMapping("/getQuestionDetail/{paperId}")
    public Result<TestQuestionDetailVo> getTestQuestionDetail(@PathVariable("paperId") Long paperId){
        TestQuestionDetailVo testQuestionDetailVo = paperService.getAllQuestionDetail(paperId);
        return Result.ok(testQuestionDetailVo);
    }

    @Operation(summary = "获取各类型试题数量和总分")
    @GetMapping("/testQuestionByType/{paperId}")
    public Result<HashMap<String, TestQuestionVo>> getQuestionByType(@PathVariable("paperId") Long paperId){
        List<TestQuestionVo> testQuestionVos = paperService.getQuestionByType(paperId);
        HashMap<String, TestQuestionVo> map = new HashMap<>();
        for (TestQuestionVo testQuestionVo : testQuestionVos) {
            switch (testQuestionVo.getQuestionType()){
                case "1": map.put("single",testQuestionVo);
                    break;
                case "2": map.put("more",testQuestionVo);
                    break;
                case "3": map.put("judge",testQuestionVo);
                    break;
                case "4": map.put("fill",testQuestionVo);
                    break;
                case "5": map.put("short",testQuestionVo);
                    break;
                case "6": map.put("problem",testQuestionVo);
                    break;
            }
        }
        return Result.ok(map);
    }



}
