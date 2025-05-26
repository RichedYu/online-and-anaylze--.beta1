package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.ChapterInfoVo;

/**
* @author Administrator
* @description 针对表【chapter】的数据库操作Service
* @createDate 2024-12-19 09:55:05
*/
public interface ChapterService extends IService<Chapter> {

    IPage<ChapterInfoVo> getChapterList(Page<ChapterInfoVo> page, String keyword);
}
