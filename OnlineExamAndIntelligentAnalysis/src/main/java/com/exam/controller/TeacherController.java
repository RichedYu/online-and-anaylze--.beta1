package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Clazz;
import com.exam.entity.Teacher;
import com.exam.service.ClazzService;
import com.exam.service.TeacherService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.ClassInfoVo;
import com.exam.vo.TeacherInfoVo;
import com.exam.vo.TeacherManageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;



/**
 * Author:CM
 * Date: 2025/1/22 21:45
 */
@Tag(name = "教师管理")
@RestController
@RequestMapping("/exam")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    // 根据eno查询教师信息
    @Operation(summary = "根据eno查询教师信息")
    @GetMapping("/teacherInfo/{eno}")
    public Result<TeacherInfoVo> getTeacherInfo(@PathVariable("eno") Long eno) {
        System.out.println("正在调用service层方法查询教师信息-");
        if (eno == null) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        TeacherInfoVo teacherInfoVo = teacherService.getTeacherInfo(eno);
        if (teacherInfoVo == null) {
            return Result.fail(ResultCodeEnum.DATA_ERROR);
        }
        return Result.ok(teacherInfoVo);
    }

    // 获得教师列表(分页)教师工号、教师姓名、所属学院名称、管理班级数量、职务、创建时间、更新时间
    @Operation(summary = "获得教师列表")
    @GetMapping("/teacherList/{current}/{size}/{keyword}")
    public Result<IPage<TeacherManageVo>> getTeacherList(@PathVariable("current") Integer current,
                                                       @PathVariable("size") Integer size,
                                                       @PathVariable(value = "keyword", required = false) String keyword) {
        System.out.println("正在调用service层方法获得分页数据-----------");
        assert current != null;
        assert size != null;
        // 调用service层方法获得分页数据
        Page<TeacherManageVo> page = new Page<>(current, size);
        IPage<TeacherManageVo> teacherManageVos = teacherService.getTeacherList(page, keyword);
        return Result.ok(teacherManageVos);
    }

    // 新增教师
    @Operation(summary = "新增教师")
    @PostMapping("/createTeacher")
    public Result<String> createTeacher(@RequestBody TeacherInfoVo teacherInfoVo) {
        System.out.println("正在调用service层方法新增教师-");
        // 数据校验
        System.out.println(teacherInfoVo.toString());

        if (teacherInfoVo.getName() == null || teacherInfoVo.getName().trim().equals("")) {
            System.out.println("教师名称不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }

        System.out.println("数据校验通过-----------正在调用service层方法保存数据-----------");
        if (teacherInfoVo.getEno() == null) {
            // 调用service层方法保存数据
            boolean flag = teacherService.saveTeacherInfo(teacherInfoVo);
            if (flag) {
                System.out.println("保存数据成功-----------正在返回成功信息-");
                return Result.ok("创建成功");
            } else {
                return Result.fail(ResultCodeEnum.FAIL);
            }
        } else {
            // 判断教师工号是否已存在
            LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Teacher::getEno, teacherInfoVo.getEno());
            Teacher teacher = teacherService.getOne(queryWrapper);
            if (teacher != null) {
                // 修改教师信息
                System.out.println("教师工号已存在-----------正在调用service层方法修改教师信息-");
                boolean flag = teacherService.updateTeacherInfo(teacherInfoVo);
                if (flag) {
                    System.out.println("修改教师信息成功-----------正在返回成功信息-");
                    return Result.ok("修改成功");
                } else {
                    return Result.fail(ResultCodeEnum.FAIL);
                }
            }
        }
        return Result.fail(ResultCodeEnum.DATA_ERROR);
    }

    // 删除班级
    @Operation(summary = "删除教师")
    @DeleteMapping("/deleteTeacher/{eno}")
    public Result<String> deleteChapter(@PathVariable("eno") Long eno) {
        System.out.println("正在调用service层方法删除教师-");
        if (eno == null) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        boolean flag = teacherService.removeByEno(eno);
        if (flag) {
            return Result.ok("删除成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }


}
