package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Chapter;
import com.exam.entity.KnowledgePoints;
import com.exam.service.ChapterService;
import com.exam.service.KnowledgePointsService;
import com.exam.utils.result.Result;
import com.exam.vo.ChapterInfoVo;
import com.exam.vo.KnowledgeInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author:CM
 * Date: 2025/3/16 21:36
 */
@Tag(name = "知识点管理")
@RestController
@RequestMapping("/exam")
public class KnowledgeController {
    @Resource
    private KnowledgePointsService knowledgePointsService;


    // 得到所有知识点信息
    @Operation(summary = "得到所有知识点信息")
    @GetMapping("/knowledgePointsList/{chapterId}")
    public Result<List<KnowledgePoints>> getKnowledgePointsList(@PathVariable("chapterId") Long chapterId) {
        System.out.println("正在调用service层方法获得所有知识点信息-");
        LambdaQueryWrapper<KnowledgePoints> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(KnowledgePoints::getChapterId, chapterId);
        List<KnowledgePoints> list = knowledgePointsService.list(wrapper);
        return Result.ok(list);
    }

    // 获得知识点列表(分页)知识点编号、知识点名称、所属章节名称、试题数量、创建时间、更新时间
    @Operation(summary = "获得知识点列表")
    @GetMapping("/knowledgePointsList/{current}/{size}/{keyword}")
    public Result<IPage<KnowledgeInfoVo>> getKnowledgePointsList(@PathVariable("current") Integer current,
                                                         @PathVariable("size") Integer size,
                                                         @PathVariable(value = "keyword", required = false) String keyword) {
        System.out.println("正在调用service层方法获得分页数据-----------");
        assert current != null;
        assert size != null;
        // 调用service层方法获得分页数据
        Page<KnowledgeInfoVo> page = new Page<>(current, size);
        IPage<KnowledgeInfoVo> chapterInfoVos = knowledgePointsService.getChapterList(page, keyword);
        return Result.ok(chapterInfoVos);
    }

}
