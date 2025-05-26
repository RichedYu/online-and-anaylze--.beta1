package com.exam.mapper;

import com.exam.dto.QuestionByChapterSumDto;
import com.exam.entity.Question;
import com.exam.entity.Score;
import com.exam.entity.ScoreDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.Answer;
import com.exam.vo.TestQuestionVo;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【score_detail】的数据库操作Mapper
* @createDate 2024-12-19 09:55:05
* @Entity com.exam.entity.ScoreDetail
*/
public interface ScoreDetailMapper extends BaseMapper<ScoreDetail> {

    List<Answer> getAnswer(Long scoreId);

    List<TestQuestionVo> getNumByQuestionType(List<Long> scoreIds);

    List<TestQuestionVo> getCorrectByQuestionType(List<Long> scoreIds);

    List<QuestionByChapterSumDto> getQuestionsByScoreIds(List<Score> scores);

    List<HashMap<String, Object>> getExamQuestionChapter(Long scoreId);

    List<HashMap<String, Double>> getExamQuestionChapterScoreRate(Long scoreId);
}




