package com.exam.service;

import com.exam.vo.KnowledgePointsAnalysisVo;
import com.exam.vo.LeiDaTuVo;
import com.exam.vo.QuestionTypeAnalysisVo;
import com.exam.vo.SubjectDetailVo;

import java.util.HashMap;
import java.util.List;

public interface AnalysisService {
    List<HashMap<String, Object>> getSubjects(String studentId);

    SubjectDetailVo getSubjectDetail(Long studentId,Long subjectId);

    List<HashMap<String, Object>> getGrade(Long studentId, Long subjectId);

    HashMap<String, List<Object>> getTrend(Long studentId, Long subjectId);

    HashMap<String, Double> getCorrectRate(Long studentId, Long subjectId);

    HashMap<String, Object> getCorrectRateByDifficulty(Long studentId, Long subjectId);

    List<QuestionTypeAnalysisVo> getExamQuestionTypeAnalysis(Long scoreId);

    List<QuestionTypeAnalysisVo> getExamQuestionDifficulty(Long scoreId);

    List<HashMap<String, Object>> getExamQuestionChapter(Long scoreId);

    LeiDaTuVo getExamQuestionChapterScoreRate(Long scoreId);

    List<KnowledgePointsAnalysisVo> getSubjectKnowledgePoint(Long subjectId,Long studentId);

    HashMap<String, Object> getSelfGrade(Long studentId, Long subjectId);

    List<KnowledgePointsAnalysisVo> getWeakSubjectKnowledgePoint(Long subjectId, Long studentId);

    List<KnowledgePointsAnalysisVo> getClassWeakSubjectKnowledgePoint(Long subjectId, Long classId);
}
