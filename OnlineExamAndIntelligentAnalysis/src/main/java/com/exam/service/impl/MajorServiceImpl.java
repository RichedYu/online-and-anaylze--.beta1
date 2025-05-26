package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.Major;
import com.exam.service.MajorService;
import com.exam.mapper.MajorMapper;
import com.exam.vo.MajorInfoVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



/**
* @author Administrator
* @description 针对表【major】的数据库操作Service实现
* @createDate 2024-12-23 11:05:18
*/
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major>
    implements MajorService{

    @Resource
    private MajorMapper majorMapper;

    @Override
    public IPage<MajorInfoVo> getMajorList(Page<MajorInfoVo> page, String keyword) {
        return majorMapper.getMajorList(page, keyword);
    }
}




