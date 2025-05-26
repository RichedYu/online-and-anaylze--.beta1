package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.StudentInfoVo;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【student】的数据库操作Service
* @createDate 2024-12-13 21:58:56
*/
public interface StudentService extends IService<Student> {

    StudentInfoVo getStudentInfo(Long studentId);

    IPage<StudentInfoVo> getStudentList(Page<StudentInfoVo> page, String keyword);

    List<HashMap<String, Object>> getClassStudent(Long classId);
}
