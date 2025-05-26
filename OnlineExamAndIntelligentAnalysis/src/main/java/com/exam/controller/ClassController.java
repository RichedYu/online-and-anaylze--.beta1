package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Chapter;
import com.exam.entity.Clazz;
import com.exam.service.ChapterService;
import com.exam.service.ClazzService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.ChapterInfoVo;
import com.exam.vo.ClassInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * Author:CM
 * Date: 2025/1/22 20:17
 */
@Tag(name = "班级管理")
@RestController
@RequestMapping("/exam")
public class ClassController {

    @Resource
    private ClazzService classService;

    // 获得班级列表(分页)班级编号、班级名称、所属专业名称、学生数量、教师ID、班主任姓名、创建时间、更新时间
    @Operation(summary = "获得班级列表")
    @GetMapping("/classList/{current}/{size}/{keyword}")
    public Result<IPage<ClassInfoVo>> getClassList(@PathVariable("current") Integer current,
                                                   @PathVariable("size") Integer size,
                                                   @PathVariable(value = "keyword", required = false) String keyword) {
        System.out.println("正在调用service层方法获得分页数据-----------");
        assert current != null;
        assert size != null;
        // 调用service层方法获得分页数据
        Page<ClassInfoVo> page = new Page<>(current, size);
        IPage<ClassInfoVo> classInfoVos = classService.getClassList(page, keyword);
        return Result.ok(classInfoVos);
    }

    // 新增班级
    @Operation(summary = "新增班级")
    @PostMapping("/createClass")
    public Result<String> createClass(@RequestBody Clazz clazz) {
        System.out.println("正在调用service层方法新增科目-");
        // 数据校验
        System.out.println(clazz.toString());

        if (clazz.getName() == null || clazz.getName().trim().equals("")) {
            System.out.println("章节名称不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }

        System.out.println("数据校验通过-----------正在调用service层方法保存数据-----------");
        // 查找数据库是否存在该科目名称
        LambdaQueryWrapper<Clazz> wr = new LambdaQueryWrapper<>();
        wr.eq(Clazz::getName, clazz.getName());
        wr.eq(Clazz::getMajorId, clazz.getMajorId());
        Clazz result = classService.getOne(wr);
        if (result != null) {
            System.out.println("该班级称已存在-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.INSERT_ERROR);
        }
        // 调用service层方法保存数据
        boolean flag = classService.save(clazz);
        if (flag) {
            System.out.println("保存数据成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 删除班级
    @Operation(summary = "删除班级")
    @DeleteMapping("/deleteClass/{classId}")
    public Result<String> deleteChapter(@PathVariable("classId") Long classId) {
        System.out.println("正在调用service层方法删除学院-");
        if (classId == null) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        boolean flag = classService.removeById(classId);
        if (flag) {
            return Result.ok("删除成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 获取所有班级
    @Operation(summary = "获取所有班级")
    @GetMapping("/getAllClass")
    public Result<List<Clazz>> getAllClass() {
        System.out.println("正在调用service层方法获得所有班级-");
        // 调用service层方法获得所有班级
        List<Clazz> clazzList = classService.list();
        return Result.ok(clazzList);
    }

    // 根据考试科目获取班级
    @Operation(summary = "根据考试科目获取班级")
    @GetMapping("/getClassBySubject/{subjectId}")
    public Result<List<HashMap<String, Object>>> getClassBySubject(@PathVariable("subjectId") Long subjectId) {
        System.out.println("正在调用service层方法获得所有班级-");
        // 调用service层方法获得所有班级
        List<HashMap<String, Object>> clazzList = classService.getClassBySubject(subjectId);
        return Result.ok(clazzList);
    }


}
