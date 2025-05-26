package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Institution;
import com.exam.entity.Major;
import com.exam.entity.Subject;
import com.exam.service.SubjectService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.MajorInfoVo;
import com.exam.vo.SubjectInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Author:CM
 * Date: 2025/1/21 23:05
 */
@Tag(name = "科目管理")
@RestController
@RequestMapping("/exam")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    // 得到所有科目信息
    @Operation(summary = "得到所有科目信息")
    @GetMapping("/subjectList")
    public Result<List<Subject>> getSubjectList() {
        System.out.println("正在调用service层方法获得所有学院信息-");
        List<Subject> list = subjectService.list();
        return Result.ok(list);
    }

    // 获得课程列表(分页)课程编号、课程名称、章节数量、试卷数量、创建时间、更新时间
    @Operation(summary = "获得课程列表")
    @GetMapping("/subjectList/{current}/{size}/{keyword}")
    public Result<IPage<SubjectInfoVo>> getSubjectList(@PathVariable("current") Integer current,
                                                       @PathVariable("size") Integer size,
                                                       @PathVariable(value = "keyword",required = false) String keyword) {
        System.out.println("正在调用service层方法获得分页数据-----------");
        assert current != null;
        assert size != null;
        // 调用service层方法获得分页数据
        Page<SubjectInfoVo> page = new Page<>(current, size);
        IPage<SubjectInfoVo> subjectInfoVos = subjectService.getSubjectList(page, keyword);
        return Result.ok(subjectInfoVos);
    }

    // 新增科目
    @Operation(summary = "新增科目")
    @PostMapping("/createSubject")
    public Result<String> createSubject(@RequestBody Subject subject) {
        System.out.println("正在调用service层方法新增科目-");
        // 数据校验
        System.out.println(subject.toString());
        if(subject == null){
            System.out.println("subject类对象为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        if(subject.getName() == null || subject.getName().trim().equals("")){
            System.out.println("科目名称不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }

        System.out.println("数据校验通过-----------正在调用service层方法保存数据-----------");
        // 查找数据库是否存在该科目名称
        LambdaQueryWrapper<Subject> wr = new LambdaQueryWrapper<>();
        wr.eq(Subject::getName, subject.getName());
        Subject result = subjectService.getOne(wr);
        if (result != null){
            System.out.println("该科目名称已存在-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.INSERT_ERROR);
        }
        // 调用service层方法保存数据
        boolean flag = subjectService.save(subject);
        if (flag) {
            System.out.println("保存数据成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 删除科目
    @Operation(summary = "删除科目")
    @DeleteMapping("/deleteSubject/{subjectId}")
    public Result<String> deleteSubject(@PathVariable("subjectId") Long subjectId) {
        System.out.println("正在调用service层方法删除学院-");
        if (subjectId == null){
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        boolean flag = subjectService.removeById(subjectId);
        if (flag) {
            return Result.ok("删除成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }


}
