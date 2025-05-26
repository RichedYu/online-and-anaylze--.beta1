package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.dto.ScoreAnalysisDto;
import com.exam.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.*;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【score】的数据库操作Service
* @createDate 2024-12-13 21:58:56
*/
public interface ScoreService extends IService<Score> {

    IPage<ScoreVo> findById(Page<Score> scorePage, Integer studentId);

    List<ScoreVo> findById(Integer studentId);

    boolean saveScore(ScoreResultVo scoreResultVo);

    Score queryScore(Long studentId,Long paperId);

    ScoreRecordVo getScoreRecord(Long scoreId);

    boolean saveScoreRecord(ScoreResultVo scoreResultVo);

    IPage<MarkingPaperVo> reviewList(Page<Score> scorePage, String keyword, Long paperId, Long classId);

    List<Answer> getAnswer(Long scoreId);

    boolean saveFinalScore(FinalScoreVo finalScoreVo);

    ScoreAnalysisDto getExamAnalysis(Long scoreId);

    List<HashMap<String, Object>> queryScoreByStudentIdAndSubjectId(Long studentId, Long subjectId);

    IPage<MarkingPaperVo> reviewed(Page<Score> scorePage, String keyword, Long paperId, Long classId);
}
