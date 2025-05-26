package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Chapter;
import com.exam.entity.Subject;
import com.exam.service.ChapterService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.ChapterInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Author:CM
 * Date: 2025/1/22 9:43
 */
@Tag(name = "章节管理")
@RestController
@RequestMapping("/exam")
public class ChapterController {
    @Resource
    private ChapterService chapterService;


    // 得到所有章节信息
    @Operation(summary = "得到所有章节信息")
    @GetMapping("/chapterList/{subjectId}")
    public Result<List<Chapter>> getChapterList(@PathVariable("subjectId") Long subjectId) {
        System.out.println("正在调用service层方法获得所有学院信息-");
        LambdaQueryWrapper<Chapter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Chapter::getSubjectId, subjectId);
        List<Chapter> list = chapterService.list(wrapper);
        return Result.ok(list);
    }

    // 获得章节列表(分页)章节编号、章节名称、所属科目名称、试题数量、创建时间、更新时间
    @Operation(summary = "获得章节列表")
    @GetMapping("/chapterList/{current}/{size}/{keyword}")
    public Result<IPage<ChapterInfoVo>> getChapterList(@PathVariable("current") Integer current,
                                                       @PathVariable("size") Integer size,
                                                       @PathVariable(value = "keyword", required = false) String keyword) {
        System.out.println("正在调用service层方法获得分页数据-----------");
        assert current != null;
        assert size != null;
        // 调用service层方法获得分页数据
        Page<ChapterInfoVo> page = new Page<>(current, size);
        IPage<ChapterInfoVo> chapterInfoVos = chapterService.getChapterList(page, keyword);
        return Result.ok(chapterInfoVos);
    }

    // 新增章节
    @Operation(summary = "新增章节")
    @PostMapping("/createChapter")
    public Result<String> createChapter(@RequestBody Chapter chapter) {
        System.out.println("正在调用service层方法新增科目-");
        // 数据校验
        System.out.println(chapter.toString());
        if (chapter == null) {
            System.out.println("subject类对象为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        if (chapter.getName() == null || chapter.getName().trim().equals("")) {
            System.out.println("章节名称不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }

        System.out.println("数据校验通过-----------正在调用service层方法保存数据-----------");
        // 查找数据库是否存在该科目名称
        LambdaQueryWrapper<Chapter> wr = new LambdaQueryWrapper<>();
        wr.eq(Chapter::getName, chapter.getName());
        wr.eq(Chapter::getSubjectId, chapter.getSubjectId());
        Chapter result = chapterService.getOne(wr);
        if (result != null) {
            System.out.println("该章节称已存在-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.INSERT_ERROR);
        }
        // 调用service层方法保存数据
        boolean flag = chapterService.save(chapter);
        if (flag) {
            System.out.println("保存数据成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 删除章节
    @Operation(summary = "删除章节")
    @DeleteMapping("/deleteChapter/{chapterId}")
    public Result<String> deleteChapter(@PathVariable("chapterId") Long chapterId) {
        System.out.println("正在调用service层方法删除学院-");
        if (chapterId == null) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        boolean flag = chapterService.removeById(chapterId);
        if (flag) {
            return Result.ok("删除成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

}
