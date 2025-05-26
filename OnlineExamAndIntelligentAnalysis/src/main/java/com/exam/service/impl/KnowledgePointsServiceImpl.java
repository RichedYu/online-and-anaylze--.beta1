package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.KnowledgePoints;
import com.exam.service.KnowledgePointsService;
import com.exam.mapper.KnowledgePointsMapper;
import com.exam.vo.KnowledgeInfoVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【knowledge_points】的数据库操作Service实现
* @createDate 2025-02-14 21:31:59
*/
@Service
public class KnowledgePointsServiceImpl extends ServiceImpl<KnowledgePointsMapper, KnowledgePoints>
    implements KnowledgePointsService{

    @Resource
    private KnowledgePointsMapper knowledgePointsMapper;

    @Override
    public IPage<KnowledgeInfoVo> getChapterList(Page<KnowledgeInfoVo> page, String keyword) {
        return knowledgePointsMapper.getChapterList(page, keyword);
    }
}




