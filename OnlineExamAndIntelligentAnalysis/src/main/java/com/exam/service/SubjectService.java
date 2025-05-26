package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.SubjectInfoVo;

/**
* @author Administrator
* @description 针对表【subject】的数据库操作Service
* @createDate 2024-12-13 21:58:56
*/
public interface SubjectService extends IService<Subject> {

    IPage<SubjectInfoVo> getSubjectList(Page<SubjectInfoVo> page, String keyword);
}
