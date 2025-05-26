package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.Chapter;
import com.exam.service.ChapterService;
import com.exam.mapper.ChapterMapper;
import com.exam.vo.ChapterInfoVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



/**
* @author Administrator
* @description 针对表【chapter】的数据库操作Service实现
* @createDate 2024-12-19 09:55:05
*/
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter>
    implements ChapterService{

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public IPage<ChapterInfoVo> getChapterList(Page<ChapterInfoVo> page, String keyword) {
        return chapterMapper.getChapterList(page, keyword);
    }
}




