package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.KnowledgePoints;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.KnowledgeInfoVo;
import com.exam.vo.KnowledgePointsAnalysisVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【knowledge_points】的数据库操作Mapper
* @createDate 2025-02-14 21:31:59
* @Entity com.exam.entity.KnowledgePoints
*/
public interface KnowledgePointsMapper extends BaseMapper<KnowledgePoints> {

    List<KnowledgePointsAnalysisVo> getSubjectKnowledgePoint(Long subjectId);

    IPage<KnowledgeInfoVo> getChapterList(Page<KnowledgeInfoVo> page, String keyword);
}




