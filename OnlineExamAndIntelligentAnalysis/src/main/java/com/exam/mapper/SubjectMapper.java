package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.SubjectInfoVo;

/**
* @author Administrator
* @description 针对表【subject】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.Subject
*/
public interface SubjectMapper extends BaseMapper<Subject> {

    IPage<SubjectInfoVo> getSubjectList(Page<SubjectInfoVo> page, String keyword);
}




