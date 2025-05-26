package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import com.exam.service.StudentService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.StudentInfoVo;
import com.exam.vo.TeacherInfoVo;
import com.exam.vo.TeacherManageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * Author:CM
 * Date: 2024/12/31 21:07
 */
@Tag(name="学生信息管理")
@RestController
@RequestMapping("/exam")
public class StudentController {

    @Resource
    private StudentService studentService;

    @Operation(summary = "根据学号获取学生信息")
    @GetMapping("/studentInfo/{studentId}")
    public Result<StudentInfoVo> getStudentInfo(@PathVariable("studentId") Long studentId){
        StudentInfoVo studentInfoVo = studentService.getStudentInfo(studentId);
        return Result.ok(studentInfoVo);
    }

    // 获得学生列表(分页)
    @Operation(summary = "获得学生列表")
    @GetMapping("/studentList/{current}/{size}/{keyword}")
    public Result<IPage<StudentInfoVo>> getStudentList(@PathVariable("current") Integer current,
                                                         @PathVariable("size") Integer size,
                                                         @PathVariable(value = "keyword", required = false) String keyword) {
        System.out.println("正在调用service层方法获得分页数据-----------");
        assert current != null;
        assert size != null;
        // 调用service层方法获得分页数据
        Page<StudentInfoVo> page = new Page<>(current, size);
        IPage<StudentInfoVo> studentInfoVos = studentService.getStudentList(page, keyword);
        return Result.ok(studentInfoVos);
    }

    // 新增学生
    @Operation(summary = "新增学生")
    @PostMapping("/createStudent")
    public Result<String> createStudent(@RequestBody StudentInfoVo studentInfoVo) {
        System.out.println("正在调用service层方法新增学生-");
        // 数据校验
        System.out.println(studentInfoVo.toString());

        if (studentInfoVo.getName() == null || studentInfoVo.getName().trim().equals("")) {
            System.out.println("教师名称不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        return Result.fail(ResultCodeEnum.DATA_ERROR);
    }

    // 删除学生
//    @Operation(summary = "删除学生")
//    @DeleteMapping("/deleteStudent/{studentId}")
//    public Result<String> deleteChapter(@PathVariable("studentId") Long studentId) {
//        System.out.println("正在调用service层方法删除学生-");
//        if (studentId == null) {
//            return Result.fail(ResultCodeEnum.PARAM_ERROR);
//        }
//        boolean flag = studentService.removeByStudentId(studentId);
//        if (flag) {
//            return Result.ok("删除成功");
//        } else {
//            return Result.fail(ResultCodeEnum.FAIL);
//        }
//    }

    // 根据班级ID获取所有班级学生
    @Operation(summary = "根据班级ID获取所有班级学生")
    @GetMapping("/getClassStudent/{classId}")
    public Result<List<HashMap<String,Object>>> getClassStudent(@PathVariable("classId") Long classId) {
        System.out.println("正在调用service层方法获得班级学生数据-");
        if (classId == null) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        // 调用service层方法获得所有学生ID，姓名
        List<HashMap<String,Object>> classStudentList = studentService.getClassStudent(classId);
        return Result.ok(classStudentList);
    }


}
