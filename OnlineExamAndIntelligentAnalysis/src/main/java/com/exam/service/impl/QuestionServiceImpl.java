package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.Answers;
import com.exam.entity.Judge;
import com.exam.entity.MultiChoice;
import com.exam.entity.Question;
import com.exam.enums.ChoiceAnswer;
import com.exam.enums.JudgeEnum;
import com.exam.enums.LevelEnum;
import com.exam.enums.QuestionType;
import com.exam.mapper.AnswersMapper;
import com.exam.mapper.JudgeMapper;
import com.exam.mapper.MultiChoiceMapper;
import com.exam.service.JudgeService;
import com.exam.service.QuestionService;
import com.exam.mapper.QuestionMapper;
import com.exam.vo.QuestionInfoVo;
import com.exam.vo.QuestionVo;
import com.exam.vo.questionType.JudgeVo;
import com.exam.vo.questionType.SingleVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* @author Administrator
* @description 针对表【question】的数据库操作Service实现
* @createDate 2024-12-13 21:58:56
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private MultiChoiceMapper multiChoiceMapper;
    @Resource
    private JudgeMapper judgeMapper;
    @Resource
    private AnswersMapper answersMapper;

    @Override
    public IPage<QuestionInfoVo> getQuestionList(Page<QuestionInfoVo> page, String keyword,Long subjectId,Long chapterId) {
        return questionMapper.getQuestionList(page, keyword,subjectId,chapterId);
    }

    @Override
    public IPage<QuestionVo> getWrongQuestion(Page<QuestionVo> pageParam, Long studentId, Long subjectId) {
        return questionMapper.getWrongQuestion(pageParam, studentId, subjectId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createSingle(SingleVo singleVo) {
        Question question = new Question();
        question.setQuestion(singleVo.getQuestion());
        question.setAnalysis(singleVo.getAnalysis());
        question.setScoreValue(2);
        question.setType(QuestionType.SINGE_CHOICE);
        question.setChapterId(singleVo.getChapterId());
        question.setLevel(LevelEnum.getEnumByCode(singleVo.getLevel()));
        question.setKnowledgePointIds(singleVo.getKnowledgePointIdsStr());
        questionMapper.insert(question);

        MultiChoice multiChoice = new MultiChoice();
        multiChoice.setQuestionId(question.getId());
        multiChoice.setOptiona(singleVo.getOptionA());
        multiChoice.setOptionb(singleVo.getOptionB());
        multiChoice.setOptionc(singleVo.getOptionC());
        multiChoice.setOptiond(singleVo.getOptionD());
        multiChoice.setCorrectAnswer(ChoiceAnswer.getEnumByCode(singleVo.getCorrectAnswers()));
        multiChoiceMapper.insert(multiChoice);

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createJudge(JudgeVo judgeVo) {
        Question question = new Question();
        question.setQuestion(judgeVo.getQuestion());
        question.setAnalysis(judgeVo.getAnalysis());
        question.setScoreValue(2);
        question.setType(QuestionType.JUDGE);
        question.setChapterId(judgeVo.getChapterId());
        question.setLevel(LevelEnum.getEnumByCode(judgeVo.getLevel()));
        question.setKnowledgePointIds(judgeVo.getKnowledgePointIdsStr());
        questionMapper.insert(question);
        Judge judge = new Judge();
        judge.setQuestionId(question.getId());
        judge.setCorrectAnswer(JudgeEnum.getEnumByCode(judgeVo.getCorrectAnswers()));
        judgeMapper.insert(judge);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createFill(JudgeVo judgeVo) {
        Question question = new Question();
        question.setQuestion(judgeVo.getQuestion());
        question.setAnalysis(judgeVo.getAnalysis());
        String[] split = judgeVo.getCorrectAnswers().split(",");
        question.setScoreValue(split.length);
        question.setType(QuestionType.FILL_BLANK);
        question.setChapterId(judgeVo.getChapterId());
        question.setLevel(LevelEnum.getEnumByCode(judgeVo.getLevel()));
        question.setKnowledgePointIds(judgeVo.getKnowledgePointIdsStr());
        questionMapper.insert(question);
        Answers answers = new Answers();
        answers.setQuestionId(question.getId());
        answers.setAnswer(judgeVo.getCorrectAnswers());
        answersMapper.insert(answers);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createShortAnswer(JudgeVo judgeVo) {
        Question question = new Question();
        question.setQuestion(judgeVo.getQuestion());
        question.setAnalysis(judgeVo.getAnalysis());
        question.setScoreValue(5);
        question.setType(QuestionType.SHORT_ANSWER);
        question.setChapterId(judgeVo.getChapterId());
        question.setLevel(LevelEnum.getEnumByCode(judgeVo.getLevel()));
        question.setKnowledgePointIds(judgeVo.getKnowledgePointIdsStr());
        questionMapper.insert(question);
        Answers answers = new Answers();
        answers.setQuestionId(question.getId());
        answers.setAnswer(judgeVo.getCorrectAnswers());
        answersMapper.insert(answers);
        return true;
    }

    @Override
    public boolean createMulti(SingleVo singleVo) {
        Question question = new Question();
        question.setQuestion(singleVo.getQuestion());
        question.setAnalysis(singleVo.getAnalysis());
        question.setScoreValue(4);
        question.setType(QuestionType.MULTI_CHOICE);
        question.setChapterId(singleVo.getChapterId());
        question.setLevel(LevelEnum.getEnumByCode(singleVo.getLevel()));
        question.setKnowledgePointIds(singleVo.getKnowledgePointIdsStr());
        questionMapper.insert(question);

        MultiChoice multiChoice = new MultiChoice();
        multiChoice.setQuestionId(question.getId());
        multiChoice.setOptiona(singleVo.getOptionA());
        multiChoice.setOptionb(singleVo.getOptionB());
        multiChoice.setOptionc(singleVo.getOptionC());
        multiChoice.setOptiond(singleVo.getOptionD());
        multiChoice.setCorrectAnswer(ChoiceAnswer.getEnumByCode(singleVo.getCorrectAnswers()));
        multiChoiceMapper.insert(multiChoice);

        return true;
    }

    @Override
    public boolean createApplication(JudgeVo judgeVo) {
        Question question = new Question();
        question.setQuestion(judgeVo.getQuestion());
        question.setAnalysis(judgeVo.getAnalysis());
        question.setScoreValue(10);
        question.setType(QuestionType.APPLICATION);
        question.setChapterId(judgeVo.getChapterId());
        question.setLevel(LevelEnum.getEnumByCode(judgeVo.getLevel()));
        question.setKnowledgePointIds(judgeVo.getKnowledgePointIdsStr());
        questionMapper.insert(question);
        Answers answers = new Answers();
        answers.setQuestionId(question.getId());
        answers.setAnswer(judgeVo.getCorrectAnswers());
        answersMapper.insert(answers);
        return true;
    }
}




