package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.TeacherInfoVo;
import com.exam.vo.TeacherManageVo;

/**
* @author Administrator
* @description 针对表【teacher】的数据库操作Service
* @createDate 2024-12-13 21:58:56
*/
public interface TeacherService extends IService<Teacher> {

    IPage<TeacherManageVo> getTeacherList(Page<TeacherManageVo> page, String keyword);

    TeacherInfoVo getTeacherInfo(Long eno);

    boolean saveTeacherInfo(TeacherInfoVo teacherInfoVo);

    boolean removeByEno(Long eno);

    boolean updateTeacherInfo(TeacherInfoVo teacherInfoVo);
}
