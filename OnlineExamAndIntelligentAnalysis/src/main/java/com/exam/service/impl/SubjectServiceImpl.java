package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.Subject;
import com.exam.service.SubjectService;
import com.exam.mapper.SubjectMapper;
import com.exam.vo.SubjectInfoVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



/**
* @author Administrator
* @description 针对表【subject】的数据库操作Service实现
* @createDate 2024-12-13 21:58:56
*/
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
    implements SubjectService{

    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public IPage<SubjectInfoVo> getSubjectList(Page<SubjectInfoVo> page, String keyword) {
        return subjectMapper.getSubjectList(page, keyword);
    }
}




