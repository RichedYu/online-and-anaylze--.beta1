package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.KnowledgePoints;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.KnowledgeInfoVo;

/**
* @author Administrator
* @description 针对表【knowledge_points】的数据库操作Service
* @createDate 2025-02-14 21:32:00
*/
public interface KnowledgePointsService extends IService<KnowledgePoints> {

    IPage<KnowledgeInfoVo> getChapterList(Page<KnowledgeInfoVo> page, String keyword);
}
