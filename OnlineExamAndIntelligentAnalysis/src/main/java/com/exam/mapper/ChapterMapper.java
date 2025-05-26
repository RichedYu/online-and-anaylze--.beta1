package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.ChapterInfoVo;

/**
* @author Administrator
* @description 针对表【chapter】的数据库操作Mapper
* @createDate 2024-12-19 09:55:05
* @Entity com.exam.entity.Chapter
*/
public interface ChapterMapper extends BaseMapper<Chapter> {

    IPage<ChapterInfoVo> getChapterList(Page<ChapterInfoVo> page, String keyword);
}




