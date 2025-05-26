package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.StudentInfoVo;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【student】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.Student
*/
public interface StudentMapper extends BaseMapper<Student> {

    StudentInfoVo getStudentInfo(Long studentId);

    Long getClassId(Long studentId);

    List<Long> getAllStudentIdByClassId(Long classId);

    IPage<StudentInfoVo> getStudentList(Page<StudentInfoVo> page, String keyword);

    List<HashMap<String, Object>> getClassStudent(Long classId);
}




