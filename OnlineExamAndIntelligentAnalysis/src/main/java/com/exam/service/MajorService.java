package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.MajorInfoVo;

/**
* @author Administrator
* @description 针对表【major】的数据库操作Service
* @createDate 2024-12-23 11:05:18
*/
public interface MajorService extends IService<Major> {

    IPage<MajorInfoVo> getMajorList(Page<MajorInfoVo> page, String keyword);
}
