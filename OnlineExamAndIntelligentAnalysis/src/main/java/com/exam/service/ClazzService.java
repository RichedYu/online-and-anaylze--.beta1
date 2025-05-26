package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.ClassInfoVo;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【class】的数据库操作Service
* @createDate 2024-12-13 21:58:56
*/
public interface ClazzService extends IService<Clazz> {

    IPage<ClassInfoVo> getClassList(Page<ClassInfoVo> page, String keyword);

    List<HashMap<String, Object>> getClassBySubject(Long subjectId);
}
