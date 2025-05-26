package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.Student;
import com.exam.service.StudentService;
import com.exam.mapper.StudentMapper;
import com.exam.vo.StudentInfoVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


/**
* @author Administrator
* @description 针对表【student】的数据库操作Service实现
* @createDate 2024-12-13 21:58:56
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Resource
    private StudentMapper studentMapper;

    @Override
    public StudentInfoVo getStudentInfo(Long studentId) {
        return studentMapper.getStudentInfo(studentId);
    }

    @Override
    public IPage<StudentInfoVo> getStudentList(Page<StudentInfoVo> page, String keyword) {
        return studentMapper.getStudentList(page, keyword);
    }

    @Override
    public List<HashMap<String, Object>> getClassStudent(Long classId) {
        return studentMapper.getClassStudent(classId);
    }
}




