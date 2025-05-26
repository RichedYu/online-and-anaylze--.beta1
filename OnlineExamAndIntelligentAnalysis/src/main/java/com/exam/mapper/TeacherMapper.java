package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.TeacherInfoVo;
import com.exam.vo.TeacherManageVo;

/**
* @author Administrator
* @description 针对表【teacher】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.Teacher
*/
public interface TeacherMapper extends BaseMapper<Teacher> {

    IPage<TeacherManageVo> getTeacherList(Page<TeacherManageVo> page, String keyword);

    TeacherInfoVo getTeacherInfo(Long eno);
}




