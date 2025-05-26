package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.dto.ScoreAnalysisDto;
import com.exam.entity.*;
import com.exam.enums.QuestionType;
import com.exam.enums.ReviewEnum;
import com.exam.exception.ExamException;
import com.exam.mapper.*;
import com.exam.service.ScoreService;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【score】的数据库操作Service实现
 * @createDate 2024-12-13 21:58:56
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score>
        implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private PaperQuestionMapper paperQuestionMapper;
    @Resource
    private ScoreDetailMapper scoreDetailMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private MultiChoiceMapper multiChoiceMapper;
    @Resource
    private JudgeMapper judgeMapper;
    @Resource
    private AnswersMapper answersMapper;
    @Resource
    private PaperMapper paperMapper;

    @Override
    public IPage<ScoreVo> findById(Page<Score> scorePage, Integer studentId) {
        return scoreMapper.findById(scorePage, studentId);
    }

    @Override
    public List<ScoreVo> findById(Integer studentId) {
        return scoreMapper.findById(studentId);
    }

    @Override
    @Transactional// 操作多表，添加事务管理
    public boolean saveScore(ScoreResultVo scoreResultVo) {
//        // 创建Score对象，存储学生分数信息
//        Score score = new Score();
//        score.setScore(scoreResultVo.getScore());
//        score.setStudentId(scoreResultVo.getStudentId());
//        score.setPaperId(scoreResultVo.getPaperId());
//        score.setAnswerDate(scoreResultVo.getAnswerDate());
//        score.setTime(scoreResultVo.getTime());
//        int insert = scoreMapper.insert(score);
//        if (insert == 0) {
//            throw new ExamException(ResultCodeEnum.DATA_ERROR);
//        }
//        // 获取试题答案，存入scoreDetail表
//        HashMap<String, String> answer = scoreResultVo.getAnswer();
//        HashMap<String, JudgeEnum> result = scoreResultVo.getResult();
//        // 遍历试题答案，保存到ScoreDetail表
//        for (Map.Entry<String, String> entry : answer.entrySet()) {
//            // 得到PaperQuestionID
//            LambdaQueryWrapper<PaperQuestion> wrapper = new LambdaQueryWrapper<>();
//            wrapper.eq(PaperQuestion::getPaperId,scoreResultVo.getPaperId())
//                    .eq(PaperQuestion::getQuestionId,entry.getKey());
//            PaperQuestion paperQuestion = paperQuestionMapper.selectOne(wrapper);
//            // 得到ScoreID
//            score = queryScore(scoreResultVo.getStudentId(),scoreResultVo.getPaperId());
//            // 得到result
//            JudgeEnum resultStr = result.get(entry.getKey());
//            // 保存到ScoreDetail表
//            ScoreDetail scoreDetail = new ScoreDetail();
//            scoreDetail.setScoreId(score.getId());
//            scoreDetail.setPaperQuestionId(paperQuestion.getId());
//            scoreDetail.setAnswer(entry.getValue());
//            scoreDetail.setResult(resultStr);
//            scoreDetailMapper.insert(scoreDetail);
//        }
        return true;
    }

    @Override
    public Score queryScore(Long studentId, Long paperId) {
        LambdaQueryWrapper<Score> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Score::getStudentId, studentId).eq(Score::getPaperId, paperId);
        return scoreMapper.selectOne(wrapper);
    }

    @Override
    public ScoreRecordVo getScoreRecord(Long scoreId) {
        Score score = scoreMapper.selectById(scoreId);
        ScoreRecordVo scoreRecordVo = new ScoreRecordVo();
        scoreRecordVo.setScore(score.getScore());
        scoreRecordVo.setAnswerDate(score.getAnswerDate());
        scoreRecordVo.setPaperId(score.getPaperId());
        scoreRecordVo.setTime(score.getTime());
        scoreRecordVo.setStudentId(score.getStudentId());

        HashMap<Long, String> answerMap = new HashMap<>();
        HashMap<Long, String> analysisMap = new HashMap<>();
        List<Answer> answers = scoreDetailMapper.getAnswer(scoreId);
        for (Answer answer : answers) {

            answerMap.put(answer.getQuestionId(), answer.getAnswer());
            analysisMap.put(answer.getQuestionId(), answer.getAnalysis());
        }

        scoreRecordVo.setUserAnswers(answerMap);
        scoreRecordVo.setAnalysis(analysisMap);
        return scoreRecordVo;
    }

    @Override
    @Transactional// 操作多表，添加事务管理
    public boolean saveScoreRecord(ScoreResultVo scoreResultVo) {
        // 验证是否重复提交
        LambdaQueryWrapper<Score> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Score::getStudentId, scoreResultVo.getStudentId())
                .eq(Score::getPaperId, scoreResultVo.getPaperId());
        Score result = scoreMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(result) || result != null) {
            throw new ExamException(ResultCodeEnum.DATA_ERROR);
        }
        // 创建Score对象，存储学生分数信息
        Score score = new Score();
        score.setReview(ReviewEnum.WAIT_REVIEW);
        score.setAnswerDate(scoreResultVo.getAnswerDate());
        score.setPaperId(scoreResultVo.getPaperId());
        score.setStudentId(scoreResultVo.getStudentId());
        score.setTime(scoreResultVo.getTime());
        scoreMapper.insert(score);
        if (ObjectUtils.isEmpty(score.getId())) {
            throw new ExamException(ResultCodeEnum.DATA_ERROR);
        }
        // 获取试题答案，存入scoreDetail表
        HashMap<String, String> answer = scoreResultVo.getAnswer();
        // 遍历试题答案，保存到ScoreDetail表
        for (Map.Entry<String, String> entry : answer.entrySet()) {
            // 得到ScoreID
            ScoreDetail scoreDetail = new ScoreDetail();
            scoreDetail.setScoreId(score.getId());
            scoreDetail.setQuestionId(Long.parseLong(entry.getKey()));
            scoreDetail.setAnswer(entry.getValue());
            Question question = questionMapper.selectById(Long.parseLong(entry.getKey()));
            if (question.getType().equals(QuestionType.SINGE_CHOICE)
                    || question.getType().equals(QuestionType.MULTI_CHOICE)) {
                LambdaQueryWrapper<MultiChoice> wrapper1 = new LambdaQueryWrapper<>();
                wrapper1.eq(MultiChoice::getQuestionId, Long.parseLong(entry.getKey()));
                MultiChoice multiChoice = multiChoiceMapper.selectOne(wrapper1);
                scoreDetail.setCorrectAnswer(multiChoice.getCorrectAnswer().getCode());
            } else if (question.getType().equals(QuestionType.JUDGE)) {
                LambdaQueryWrapper<Judge> wrapper1 = new LambdaQueryWrapper<>();
                wrapper1.eq(Judge::getQuestionId, Long.parseLong(entry.getKey()));
                Judge judge = judgeMapper.selectOne(wrapper1);
                scoreDetail.setCorrectAnswer(judge.getCorrectAnswer().getCode());
            } else {
                LambdaQueryWrapper<Answers> wrapper1 = new LambdaQueryWrapper<>();
                wrapper1.eq(Answers::getQuestionId, Long.parseLong(entry.getKey()));
                Answers answers = answersMapper.selectOne(wrapper1);
                scoreDetail.setCorrectAnswer(answers.getAnswer());
            }
            scoreDetailMapper.insert(scoreDetail);
        }
        return true;
    }

    @Override
    public IPage<MarkingPaperVo> reviewList(Page<Score> scorePage, String keyword, Long paperId, Long classId) {
        return scoreMapper.reviewList(scorePage, keyword, paperId, classId);
    }

    @Override
    public List<Answer> getAnswer(Long scoreId) {
        return scoreMapper.getAnswer(scoreId);
    }

    @Override
    @Transactional// 操作多表，添加事务管理
    public boolean saveFinalScore(FinalScoreVo finalScoreVo) {
        // 保存最终得分
        Score score = new Score();
        score.setId(finalScoreVo.getScoreId());
        score.setScore(finalScoreVo.getFinalScore());
        score.setReview(ReviewEnum.REVIEWED);
        scoreMapper.updateById(score);
        // 保存各题目得分
        HashMap<Long, Object> scoreList = finalScoreVo.getScoreList();
        for (Long questionId : scoreList.keySet()) {
            LambdaQueryWrapper<ScoreDetail> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ScoreDetail::getScoreId, finalScoreVo.getScoreId())
                   .eq(ScoreDetail::getQuestionId, questionId);
            ScoreDetail scoreDetail = scoreDetailMapper.selectOne(wrapper);
            Double questionScore = Double.parseDouble(String.valueOf(scoreList.get(questionId)));
            scoreDetail.setScore(questionScore);
            scoreDetailMapper.updateById(scoreDetail);
        }
        return true;
    }

    @Override
    public ScoreAnalysisDto getExamAnalysis(Long scoreId) {
        ScoreAnalysisDto scoreAnalysisDto = scoreMapper.getExamInfo(scoreId);
        List<QuestionInfoVo> questionInfoVoList = scoreMapper.getQuestionInfo(scoreId);
        scoreAnalysisDto.setQuestionInfoVoList(questionInfoVoList);
        return scoreAnalysisDto;
    }

    @Override
    public List<HashMap<String, Object>> queryScoreByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        LambdaQueryWrapper<Score> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Score::getStudentId, studentId);
        List<Score> scores = scoreMapper.selectList(wrapper);
        for (Score score : scores) {
            Paper paper = paperMapper.selectById(score.getPaperId());
            if (paper.getSubjectId().equals(subjectId)) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("value",score.getId());
                map.put("label",paper.getName());
                map.put("score",score.getScore());
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public IPage<MarkingPaperVo> reviewed(Page<Score> scorePage, String keyword, Long paperId, Long classId) {
        return scoreMapper.reviewed(scorePage, keyword, paperId, classId);
    }
}




