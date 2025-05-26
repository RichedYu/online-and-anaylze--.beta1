package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.dto.ScoreAnalysisDto;
import com.exam.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.*;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【score】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.Score
*/
public interface ScoreMapper extends BaseMapper<Score> {

    IPage<ScoreVo> findById(Page<Score> scorePage, Integer studentId);

    List<ScoreVo> findById(Integer studentId);

    List<Score> selectByStudentIdAndSubjectId(Long studentId, Long subjectId);

    IPage<MarkingPaperVo> reviewList(Page<Score> scorePage, String keyword, Long paperId, Long classId);

    List<Answer> getAnswer(Long scoreId);

    ScoreAnalysisDto getExamInfo(Long scoreId);

    List<QuestionInfoVo> getQuestionInfo(Long scoreId);

    List<QuestionTypeAnalysisVo> getExamQuestionTypeAnalysis(Long scoreId);

    List<QuestionTypeAnalysisVo> getExamQuestionDifficulty(Long scoreId);

    HashMap<String, Object> getSubjectKnowledgePoint(Long subjectId, Long studentId, Long knowledgePointId);

    IPage<MarkingPaperVo> reviewed(Page<Score> scorePage, String keyword, Long paperId, Long classId);

    HashMap<String, Object> getClassSubjectKnowledgePointScore(Long subjectId, Long classId, Long knowledgePointId);
}




