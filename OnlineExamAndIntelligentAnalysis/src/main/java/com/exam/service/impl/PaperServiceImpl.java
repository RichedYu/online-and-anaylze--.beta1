package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.*;
import com.exam.enums.QuestionType;
import com.exam.mapper.*;
import com.exam.service.PaperService;
import com.exam.vo.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【paper】的数据库操作Service实现
* @createDate 2024-12-13 21:58:56
*/
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper>
    implements PaperService{

    @Resource
    private PaperMapper paperMapper;
    @Resource
    private MultiChoiceMapper multiChoiceMapper;
    @Resource
    private JudgeMapper judgeMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private PaperQuestionMapper paperQuestionMapper;
    @Resource
    private ClassPaperMapper ClassPaperMapper;
    @Resource
    private AnswersMapper AnswersMapper;

    @Override
    public List<PaperVo> papers() {
        return paperMapper.getAllPapers();
    }

    @Override
    public IPage<PaperVo> papers(Page<PaperVo> paperVoPage) {
        return paperMapper.getAllPapers(paperVoPage);
    }

    @Override
    public PaperDetailVo getPaperDetailById(Long paperId) {
        return paperMapper.getPaperDetailById(paperId);
    }

    @Override
    public TestQuestionDetailVo getAllQuestionDetail(Long paperId) {
        List<TestQuestionVo> testQuestionVos = paperMapper.getQuestionNumScore(paperId);
        List<MultiChoiceVo> multiChoiceVos = multiChoiceMapper.getAllDetailByPaperId(paperId);
        List<JudgeVo> judgeVos = judgeMapper.getAllDetailByPaperId(paperId);

        return new TestQuestionDetailVo(testQuestionVos,multiChoiceVos,judgeVos);
    }

    @Override
    public List<TestQuestionVo> getQuestionByType(Long paperId) {
        return paperMapper.getQuestionNumScore(paperId);
    }

    @Override
    public SortByTimePapersVo sortByTime() {

        List<PaperVo> allPapers = paperMapper.getAllPapers(); // 返回所有考试信息的列表
        return getSortByTimePapersVo(allPapers);
    }

    @Override
    public IPage<PaperVo> getPapersByStudentId(Page<PaperVo> paperVoPage, Long studentId,String key,String time) {
        return paperMapper.getAllPapersByStudentId(paperVoPage,studentId,key,time);
    }

    @Override
    public SortByTimePapersVo sortByTime(Long studentId) {
        List<PaperVo> allPapersByStudentId = paperMapper.getAllPapersByStudentId(studentId,null,null);
        return getSortByTimePapersVo(allPapersByStudentId);
    }

    @Override
    public List<PaperVo> getPapersByStudentId(Long studentId,String key) {
        return paperMapper.getAllPapersByStudentId(studentId,key,null);
    }

    @Override
    public ExamInfoVo getExamInfo(Long examId) {
        ExamInfoVo examInfoVo = new ExamInfoVo();
        Paper paper = paperMapper.selectById(examId);
        if (Objects.nonNull(paper)) {
            examInfoVo.setExamId(paper.getId());
            examInfoVo.setExamName(paper.getName());
            examInfoVo.setTotalScore(paper.getTotalScore());
            examInfoVo.setDuration(paper.getExamDuration());
        }

        List<QuestionByTypeVo> questionByTypeVoList = paperMapper.getQuestionByType(examId);
        for (QuestionByTypeVo questionByTypeVo : questionByTypeVoList) {
            List<Answer> answers = paperMapper
                    .getAnswersByQuestionType(examId,questionByTypeVo.getQuestionType().getCode());
            questionByTypeVo.setAnswers(answers);
        }
        examInfoVo.setQuestionByTypeVoList(questionByTypeVoList);
        return examInfoVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createExam(CreateExamVo createExamVo) {
        Paper paper = new Paper();
        // 设置考试信息
        paper.setName(createExamVo.getExamName());
        paper.setSubjectId(createExamVo.getSubjectId());
        paper.setExamTime(createExamVo.getStartTime());
        paper.setExamDuration(createExamVo.getDuration());
        paper.setTotalScore(createExamVo.getTotalScore());
        int insert = paperMapper.insert(paper);
        if (insert <= 0) {
            throw new RuntimeException("创建考试失败");
        }
        // 设置试题信息
        Long subjectId = createExamVo.getSubjectId();
        // 单选题
        boolean b = insertPaperQuestion(createExamVo.getSingleChoiceCount(), paper.getId(),subjectId, QuestionType.SINGE_CHOICE.getCode());
        if (!b) {
            throw new RuntimeException("创建单选题失败");
        }
        // 多选题
        b = insertPaperQuestion(createExamVo.getMultiChoiceCount(), paper.getId(),subjectId, QuestionType.MULTI_CHOICE.getCode());
        if (!b) {
            throw new RuntimeException("创建多选题失败");
        }
        // 判断题
        b = insertPaperQuestion(createExamVo.getTrueFalseCount(), paper.getId(),subjectId, QuestionType.JUDGE.getCode());
        if (!b) {
            throw new RuntimeException("创建判断题失败");
        }
        // 填空题
        b = insertPaperQuestion(createExamVo.getFillInBlankCount(), paper.getId(),subjectId, QuestionType.FILL_BLANK.getCode());
        if (!b) {
            throw new RuntimeException("创建填空题失败");
        }
        // 简答题
        b = insertPaperQuestion(createExamVo.getShortAnswerCount(), paper.getId(),subjectId, QuestionType.SHORT_ANSWER.getCode());
        // 应用题
        b = insertPaperQuestion(createExamVo.getApplicationCount(), paper.getId(),subjectId, QuestionType.APPLICATION.getCode());
        if (!b) {
            throw new RuntimeException("创建应用题失败");
        }
        // 设置班级信息
        for (Long classId : createExamVo.getClasses()) {
            ClassPaper classPaper = new ClassPaper();
            classPaper.setPaperId(paper.getId());
            classPaper.setClassId(classId);
            int insert1 = ClassPaperMapper.insert(classPaper);
            if (insert1 <= 0) {
                throw new RuntimeException("创建班级信息失败");
            }
        }
    }

    // 以下为私有方法
    private boolean insertPaperQuestion(Integer count, Long paperId,Long subjectId, String questionType) {
        if(count <= 0){
            return true;
        }

        List<Question> questions = questionMapper.getQuestionBySubjectIdAndType(subjectId, questionType);
        if(questions.isEmpty()){
            System.out.println("没有可用的试题");
            return false;
        }

        for (int i = 0; i < count; i++) {

            PaperQuestion paperQuestion = new PaperQuestion();
            paperQuestion.setPaperId(paperId);
            // 随机抽取一道题，在0到questions.size()-1之间随机取一个数，
            int index = (int) (Math.random() * questions.size());
            if(questionType.equals(QuestionType.FILL_BLANK.getCode())){
                LambdaQueryWrapper<Answers> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Answers::getQuestionId,questions.get(index).getId());
                Answers answers = AnswersMapper.selectOne(wrapper);
                if(answers == null){
                    return false;
                }
                String[] split = answers.getAnswer().split(",");
                if(i+split.length > count){
                    i--;
                    continue;
                }else {
                    i += split.length-1;
                }
            }
            paperQuestion.setQuestionId(questions.get(index).getId());
            int insert1 = paperQuestionMapper.insert(paperQuestion);
            if (insert1 <= 0) {
                System.out.println("插入试题失败");
                return false;
            }
            questions.remove(index);
        }
        return true;
    }

    public SortByTimePapersVo getSortByTimePapersVo(List<PaperVo> allPapers){
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault(); // 获取系统默认时区
        // 使用stream API对列表进行分区
        List<PaperVo> notStarted = allPapers.stream()
                .filter(paper -> paper.getExamTime().toInstant().atZone(zoneId).toLocalDateTime().isAfter(now))
                .collect(Collectors.toList());

        List<PaperVo> inProgress = allPapers.stream()
                .filter(paper -> {
                    LocalDateTime examTime = paper.getExamTime().toInstant().atZone(zoneId).toLocalDateTime();
                    LocalDateTime endTime = examTime.plusMinutes(paper.getExamDuration()); // 假设getExamDuration()返回考试持续时间（分钟）
                    return !now.isBefore(examTime) && now.isBefore(endTime);
                })
                .collect(Collectors.toList());

        List<PaperVo> ended = allPapers.stream()
                .filter(paper -> paper.getExamTime().toInstant().atZone(zoneId).toLocalDateTime().plusMinutes(paper.getExamDuration()).isBefore(now))
                .collect(Collectors.toList());

        // 构造返回对象
        SortByTimePapersVo result = new SortByTimePapersVo();
        result.setAll(allPapers.size());
        result.setNotStarted(notStarted.size()); // 接受int类型的参数，表示数量
        result.setInProgress(inProgress.size()); // 同上
        result.setEnded(ended.size()); // 同上
        return result;
    }
}




