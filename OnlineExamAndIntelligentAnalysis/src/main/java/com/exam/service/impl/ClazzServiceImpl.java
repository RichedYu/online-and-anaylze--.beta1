package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.Clazz;
import com.exam.service.ClazzService;
import com.exam.mapper.ClazzMapper;
import com.exam.vo.ClassInfoVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


/**
* @author Administrator
* @description 针对表【class】的数据库操作Service实现
* @createDate 2024-12-13 21:58:56
*/
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz>
    implements ClazzService{

    @Resource
    private ClazzMapper clazzMapper;

    @Override
    public IPage<ClassInfoVo> getClassList(Page<ClassInfoVo> page, String keyword) {
        return clazzMapper.getClassList(page, keyword);
    }

    @Override
    public List<HashMap<String, Object>> getClassBySubject(Long subjectId) {
        return clazzMapper.getClassBySubject(subjectId);
    }
}




