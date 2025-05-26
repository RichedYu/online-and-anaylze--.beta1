package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Major;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.MajorInfoVo;

/**
* @author Administrator
* @description 针对表【major】的数据库操作Mapper
* @createDate 2024-12-23 11:05:18
* @Entity com.exam.entity.Major
*/
public interface MajorMapper extends BaseMapper<Major> {

    IPage<MajorInfoVo> getMajorList(Page<MajorInfoVo> page, String keyword);
}




