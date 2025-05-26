package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exam.dto.QuestionByChapterSumDto;
import com.exam.entity.*;
import com.exam.enums.LevelEnum;
import com.exam.enums.MasteryLevelEnum;
import com.exam.enums.ReviewEnum;
import com.exam.exception.ExamException;
import com.exam.mapper.*;
import com.exam.service.AnalysisService;
import com.exam.service.KnowledgeMasteryService;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.*;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.*;


/**
 * Author:CM
 * Date: 2025/1/17 10:48
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private PaperMapper paperMapper;
    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ClassPaperMapper classPaperMapper;
    @Resource
    private ScoreDetailMapper scoreDetailMapper;
    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private KnowledgePointsMapper knowledgePointsMapper;
    @Resource
    private KnowledgeMasteryService knowledgeMasteryService;

    @Override
    public List<HashMap<String, Object>> getSubjects(String studentId) {
        List subjects = new ArrayList<>();
        LambdaQueryWrapper<Score> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Score::getStudentId, studentId).eq(Score::getReview, ReviewEnum.REVIEWED);
        List<Score> scores = scoreMapper.selectList(queryWrapper);
        if (scores.size() > 0) {
            Set<Long> subjectIds = new HashSet<>();
            for (Score score : scores) {
                Paper paper = paperMapper.selectById(score.getPaperId());
                subjectIds.add(paper.getSubjectId());
            }
            for (Long subjectId : subjectIds) {
                Subject subject = subjectMapper.selectById(subjectId);
                HashMap<String, Object> map = new HashMap<>();
                map.put("value",subject.getId());
                map.put("label",subject.getName());
                subjects.add(map);

            }
        }
        return subjects;
    }

    @Override
    public SubjectDetailVo getSubjectDetail(Long studentId,Long subjectId) {
        SubjectDetailVo subjectDetailVo = new SubjectDetailVo();
        // 获得该科目名称
        Subject subject = subjectMapper.selectById(subjectId);
        subjectDetailVo.setSubjectName(subject.getName());
        // 获取考试次数/最高分/最低分/平均分/等级
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scoreLambdaQueryWrapper.eq(Score::getStudentId, studentId).eq(Score::getReview, ReviewEnum.REVIEWED);
        List<Score> scores = scoreMapper.selectList(scoreLambdaQueryWrapper);
        ArrayList<Double> scoresList = new ArrayList<>();
        for (Score score : scores) {
            Paper paper = paperMapper.selectById(score.getPaperId());
            if (paper.getSubjectId().equals(subjectId)) {
                scoresList.add(score.getScore());
            }
        }
        if (scoresList.size() > 0) {
            subjectDetailVo.setExamCount(scoresList.size());// 考试次数
            subjectDetailVo.setMaxScore(Collections.max(scoresList));// 最高分
            subjectDetailVo.setMinScore(Collections.min(scoresList));// 最低分
            // 平均分
            int sum = 0;
            for (int i = 0; i < scoresList.size(); i++) {
                sum += scoresList.get(i);
            }
            double avgScore = (double) sum / scoresList.size();
            avgScore = (double) (int) (avgScore * 100) / 100;
            subjectDetailVo.setAvgScore(avgScore);// 平均分
            // 等级
            if (subjectDetailVo.getAvgScore() >= 90) {
                subjectDetailVo.setLevel("优秀");
            } else if (subjectDetailVo.getAvgScore() >= 80) {
                subjectDetailVo.setLevel("良好");
            } else if (subjectDetailVo.getAvgScore() >= 70) {
                subjectDetailVo.setLevel("中等");
            } else if (subjectDetailVo.getAvgScore() >= 60) {
                subjectDetailVo.setLevel("及格");
            } else {
                subjectDetailVo.setLevel("不及格");
            }
        }
        return subjectDetailVo;
    }

    @Override
    public List<HashMap<String, Object>> getGrade(Long studentId, Long subjectId) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getStudentId, studentId);
        Student student = studentMapper.selectOne(studentLambdaQueryWrapper);
        if (student == null) {
            throw new ExamException(ResultCodeEnum.STUDENT_NOT_EXIST_ERROR);
        }
        // 得到所有班级学生信息
        studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getClassId, student.getClassId());
        List<Student> students = studentMapper.selectList(studentLambdaQueryWrapper);
        System.out.println("班级学生信息：" + students);
        // 分析班级所有学生的该科目成绩等级分布
        List<HashMap<String, Object>> gradeList = new ArrayList<>();
        HashMap<String, Object> a = new HashMap<>();
        a.put("name", "优秀(>=90)");
        a.put("value", 0);
        gradeList.add(a);
        HashMap<String, Object> b = new HashMap<>();
        b.put("name", "良好(>=80)");
        b.put("value", 0);
        gradeList.add(b);
        HashMap<String, Object> c = new HashMap<>();
        c.put("name", "中等(>=70)");
        c.put("value", 0);
        gradeList.add(c);
        HashMap<String, Object> d = new HashMap<>();
        d.put("name", "及格(>=60)");
        d.put("value", 0);
        gradeList.add(d);
        HashMap<String, Object> e = new HashMap<>();
        e.put("name", "不及格(<60)");
        e.put("value", 0);
        gradeList.add(e);
        // 创建ArrayList保存所有学生的平均分
        List<Double> avgList = new ArrayList<>();
        for (Student s : students) {// 遍历班级所有学生
            LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
            scoreLambdaQueryWrapper.eq(Score::getStudentId, s.getStudentId()).eq(Score::getReview, ReviewEnum.REVIEWED);
            List<Score> scores = scoreMapper.selectList(scoreLambdaQueryWrapper);// 得到该学生所有成绩
            if (scores.size() > 0) {
                List<Double> subjectScores = new ArrayList<>();// 该学生该科目所有成绩
                for (Score score : scores) {// 遍历该学生所有成绩
                    Paper paper = paperMapper.selectById(score.getPaperId());
                    if (paper.getSubjectId().equals(subjectId)) {// 找到该科目的成绩
                        subjectScores.add(score.getScore());
                    }
                }
                // 分析该学生该科目成绩等级 优秀/良好/中等/及格/不及格
                if (subjectScores.size() > 0) {
                    // 算出平均分
                    int sum = 0;
                    for (int i = 0; i < subjectScores.size(); i++) {
                        sum += subjectScores.get(i);
                    }
                    // 平均分保留两位小数
                    double avgScore = (double) sum / subjectScores.size();
                    avgScore = (double) (int) (avgScore * 100) / 100;
                    avgList.add(avgScore);
                    // 得到该学生该科目等级
                    if (avgScore >= 90) {
                       gradeList.get(0).put("value", (int) gradeList.get(0).get("value") + 1);
                    } else if (avgScore >= 80) {
                        gradeList.get(1).put("value", (int) gradeList.get(1).get("value") + 1);
                    } else if (avgScore >= 70) {
                       gradeList.get(2).put("value", (int) gradeList.get(2).get("value") + 1);
                    } else if (avgScore >= 60) {
                        gradeList.get(3).put("value", (int) gradeList.get(3).get("value") + 1);
                    } else {
                       gradeList.get(4).put("value", (int) gradeList.get(4).get("value") + 1);
                    }
                }
            }
        }


        return gradeList;
    }

    @Override
    public HashMap<String, Object> getSelfGrade(Long studentId, Long subjectId) {
        HashMap<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getStudentId, studentId);
        Student student = studentMapper.selectOne(studentLambdaQueryWrapper);
        if (student == null) {
            throw new ExamException(ResultCodeEnum.STUDENT_NOT_EXIST_ERROR);
        }
        // 得到所有班级学生信息
        studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getClassId, student.getClassId());
        List<Student> students = studentMapper.selectList(studentLambdaQueryWrapper);
        System.out.println("班级学生信息：" + students);
        // 创建ArrayList保存所有学生的平均分
        List<Double> avgList = new ArrayList<>();
        for (Student s : students) {// 遍历班级所有学生
            LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
            scoreLambdaQueryWrapper.eq(Score::getStudentId, s.getStudentId()).eq(Score::getReview, ReviewEnum.REVIEWED);
            List<Score> scores = scoreMapper.selectList(scoreLambdaQueryWrapper);// 得到该学生所有成绩
            if (scores.size() > 0) {
                List<Double> subjectScores = new ArrayList<>();// 该学生该科目所有成绩
                for (Score score : scores) {// 遍历该学生所有成绩
                    Paper paper = paperMapper.selectById(score.getPaperId());
                    if (paper.getSubjectId().equals(subjectId)) {// 找到该科目的成绩
                        subjectScores.add(score.getScore());
                    }
                }
                // 分析该学生该科目成绩等级 优秀/良好/中等/及格/不及格
                if (subjectScores.size() > 0) {
                    // 算出平均分
                    int sum = 0;
                    for (int i = 0; i < subjectScores.size(); i++) {
                        sum += subjectScores.get(i);
                    }
                    // 平均分保留两位小数
                    double avgScore = (double) sum / subjectScores.size();
                    avgScore = (double) (int) (avgScore * 100) / 100;
                    avgList.add(avgScore);
                }
            }
        }
        SubjectDetailVo subjectDetail = getSubjectDetail(studentId, subjectId);
        // 遍历班级所有学生的平均分，算出自己在班级所处百分比
        int count = 0;
        for (Double avg : avgList) {
            if(avg < subjectDetail.getAvgScore()){
                count++;
            }
        }
        double percent = (double) count / avgList.size();
        map.put("percent", percent);
        map.put("avgScore", subjectDetail.getAvgScore());
        map.put("level", subjectDetail.getLevel());
        if(percent >= 0.8){
            map.put("standard", "上游");
            map.put("message", "继续保持哦！");
        }else if(percent >= 0.6){
            map.put("standard", "中上游");
            map.put("message", "继续加油哦！");
        }else if(percent >= 0.4){
            map.put("standard", "中游");
            map.put("message", "继续加油哦！");
        }else if(percent >= 0.2){
            map.put("standard", "中下游");
            map.put("message", "继续努力哦！");
        }else{
            map.put("standard", "下游");
            map.put("message", "需要加把劲哦！");
        }
        return map;
    }

    @Override
    public List<KnowledgePointsAnalysisVo> getWeakSubjectKnowledgePoint(Long subjectId, Long studentId) {
        List<KnowledgePointsAnalysisVo> subjectKnowledgePoint = getSubjectKnowledgePoint(subjectId, studentId);
        // 按得分率从小到大排序
        subjectKnowledgePoint.sort(Comparator.comparing(KnowledgePointsAnalysisVo::getScoreRate));
        // 取前八个知识点
        List<KnowledgePointsAnalysisVo> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i < subjectKnowledgePoint.size()) {
                result.add(subjectKnowledgePoint.get(i));
            }
        }
        return result;
    }

    @Override
    public List<KnowledgePointsAnalysisVo> getClassWeakSubjectKnowledgePoint(Long subjectId, Long classId) {
        // 根据subjectId找到该科目的所有知识点信息
        List<KnowledgePointsAnalysisVo> knowledgePoints = knowledgePointsMapper.getSubjectKnowledgePoint(subjectId);
        // 根据subjectId和classId找到该班级所有学生的该科目的成绩信息
        for (KnowledgePointsAnalysisVo knowledgePoint : knowledgePoints) {
            Long knowledgePointId = knowledgePoint.getId();
            HashMap<String, Object> knowledgePointScore =
                    scoreMapper.getClassSubjectKnowledgePointScore(subjectId, classId, knowledgePointId);
            Long questionCount = (Long) knowledgePointScore.get("questionCount");
            BigDecimal wc = (BigDecimal) knowledgePointScore.get("wrongCount");
            Long wrongCount = 0L;
            if(wc!= null){
                wrongCount = wc.longValue();
            }
            Double scoreRate = (Double) knowledgePointScore.get("scoreRate");
            BigDecimal wr = (BigDecimal) knowledgePointScore.get("wrongRate");
            Double wrongRate = 0.0;
            if(wr!= null){
                wrongRate = wr.doubleValue();
                wrongRate = (double) (int) (wrongRate * 100) / 100;
            }
            // 保留两位小数
            if(scoreRate!= null) {
                scoreRate = (double) (int) (scoreRate * 100) / 100;
            }else {
                scoreRate = 0.0;
            }

            Double judge = questionCount * scoreRate;
            knowledgePoint.setQuestionCount(questionCount);
            knowledgePoint.setWrongCount(wrongCount);
            knowledgePoint.setScoreRate(scoreRate);
            knowledgePoint.setWrongRate(wrongRate);

        }
        // 去除没有考过的知识点
        knowledgePoints.removeIf(next -> next.getQuestionCount() == 0);
        // 按得分率从小到大排序
        knowledgePoints.sort(Comparator.comparing(KnowledgePointsAnalysisVo::getScoreRate));
        // 取前八个知识点
        List<KnowledgePointsAnalysisVo> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i < knowledgePoints.size()) {
                result.add(knowledgePoints.get(i));
            }
        }
        return result;
    }


    @Override
    public HashMap<String, List<Object>> getTrend(Long studentId, Long subjectId) {
        HashMap<String, List<Object>> map = new HashMap<>();
        // 通过学号获得班级ID
        Long classId = studentMapper.getClassId(studentId);
        if (classId == null) {
            throw new ExamException(ResultCodeEnum.STUDENT_NOT_EXIST_ERROR);
        }
        // 通过班级ID获取班级所有学生ID
        List<Long> studentIds = studentMapper.getAllStudentIdByClassId(classId);
        // 找到这个班级最近该科目考试结束的六张试卷ID
        List<Long> paperIds = classPaperMapper.getClassPaper(classId, subjectId, 6);
        // 遍历试卷ID，获取班级考试平均数和个人分数
        List<Object> avgList = new ArrayList<>();
        List<Object> personalList = new ArrayList<>();
        // 考试日期
        List<Object> dateList = new ArrayList<>();
        for (Long paperId : paperIds) {
            LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
            scoreLambdaQueryWrapper.eq(Score::getPaperId, paperId).eq(Score::getReview,ReviewEnum.REVIEWED);
            List<Score> scores = scoreMapper.selectList(scoreLambdaQueryWrapper);
            if (scores.size() > 0) {
                // 找到该试卷所有学生的分数,计算平均分,保留两位小数
                Double sum = 0.0;
                for (Score score : scores) {
                    if (studentIds.contains(score.getStudentId())) {
                        sum += score.getScore();
                    }

                }
                Double avg = (double) sum / scores.size();
                avg = (double) (int) (avg * 100) / 100;
                avgList.add(avg);
                // 根据paperId和studentId找到个人分数
                scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
                scoreLambdaQueryWrapper.eq(Score::getPaperId, paperId);
                scoreLambdaQueryWrapper.eq(Score::getStudentId, studentId).eq(Score::getReview,ReviewEnum.REVIEWED);
                Score score = scoreMapper.selectOne(scoreLambdaQueryWrapper);
                // 得到考试日期
                Paper paper = paperMapper.selectById(paperId);
                Date examTime = paper.getExamTime();
                // 格式化日期,设置为zh_CN的日期格式,yyyy-MM-dd
                String dateStr = DateFormatUtils.format(examTime, "yy-MM-dd", Locale.CHINA);
                dateList.add(dateStr);
                if (score != null) {
                    personalList.add(score.getScore());
                }else {
                    personalList.add(0.0);
                    }
            }

        }
        // 保存到map中
        map.put("classAvgList", avgList);
        map.put("personalList", personalList);
        map.put("dateList", dateList);

        return map;
    }

    @Override
    public HashMap<String, Double> getCorrectRate(Long studentId, Long subjectId) {
        HashMap<String, Double> map = new HashMap<>();
        // 找到该学生所有成绩
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scoreLambdaQueryWrapper.eq(Score::getStudentId, studentId).eq(Score::getReview, ReviewEnum.REVIEWED);
        List<Score> scores = scoreMapper.selectList(scoreLambdaQueryWrapper);
        // 找到该科目的成绩
        List<Long> scoreIds = new ArrayList<>();// 该科目的所有成绩ID
        for (Score score : scores) {
            Paper paper = paperMapper.selectById(score.getPaperId());
            if (paper.getSubjectId().equals(subjectId)) {
                scoreIds.add(score.getId());
            }
        }
        // 得到该科目各题型的总数量
        System.out.println("==========scoreIds:" + scoreIds);
        List<TestQuestionVo> numByQuestionType = scoreDetailMapper.getNumByQuestionType(scoreIds);
        // 得到该科目各题型的正确数量
        List<TestQuestionVo> correctByQuestionType = scoreDetailMapper.getCorrectByQuestionType(scoreIds);
        System.out.println("============================numByQuestionType:" + numByQuestionType);
        System.out.println("============================correctByQuestionType:" + correctByQuestionType);
        // 计算各题型的正确率
        for (TestQuestionVo num : numByQuestionType) {
            for (TestQuestionVo correct : correctByQuestionType) {
                if (num.getQuestionType().equals(correct.getQuestionType())) {
                    // 单选题正确率,保留两位小数
                    double rate = (double) correct.getQuestionNumByType() / num.getQuestionNumByType();
                    rate = (double) (int) (rate * 100) / 100;
                    if (num.getQuestionType().equals("1")) {
                        map.put("单选题-"+num.getQuestionNumByType(), rate);
                    }else if (num.getQuestionType().equals("2")) {
                        map.put("多选题-"+num.getQuestionNumByType(), rate);
                    }else if (num.getQuestionType().equals("3")) {
                        map.put("判断题-"+num.getQuestionNumByType(), rate);
                    }else if (num.getQuestionType().equals("4")) {
                        map.put("填空题-"+num.getQuestionNumByType(), rate);
                    }else if (num.getQuestionType().equals("5")) {
                        map.put("简答题-"+num.getQuestionNumByType(), rate);
                    }else if (num.getQuestionType().equals("6")) {
                        map.put("应用题-"+num.getQuestionNumByType(), rate);
                    }
                }
            }

        }
        // 保存到map中
        return map;
    }

    @Override
    public HashMap<String, Object> getCorrectRateByDifficulty(Long studentId, Long subjectId) {
        // 初始化map
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Double> easyMap = new HashMap<>();
        HashMap<String, Double> mediumMap = new HashMap<>();
        HashMap<String, Double> hardMap = new HashMap<>();
        // 根据subjectId找到该科目的所有章节信息
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getSubjectId, subjectId);
        List<Chapter> chapters = chapterMapper.selectList(chapterLambdaQueryWrapper);
        // 根据studentId找到学生该科目的所有成绩信息
        List<Score> scores = scoreMapper.selectByStudentIdAndSubjectId(studentId, subjectId);
        // 根据scoreId找到scoreDetail信息关联题目信息（章节、难度）
        List<QuestionByChapterSumDto> questions = null;
        if(scores.size() > 0){
            questions= scoreDetailMapper.getQuestionsByScoreIds(scores);
        }else {
            throw new ExamException(ResultCodeEnum.NOT_FOUND_INFO);
        }
        // 遍历章节信息
        for (Chapter chapter : chapters) {
            // 统计每个章节的简单、中等、困难题型的正确率
            getCorrectRateByDifficulty(chapter,questions,easyMap, LevelEnum.EASY);
            getCorrectRateByDifficulty(chapter,questions,mediumMap, LevelEnum.MEDIUM);
            getCorrectRateByDifficulty(chapter,questions,hardMap, LevelEnum.HARD);

        }
        // 保存到map中
        map.put("简单",easyMap);
        map.put("中等",mediumMap);
        map.put("困难",hardMap);
        ArrayList<String> chapterList = new ArrayList<>();
        for (Chapter chapter : chapters) {
            chapterList.add(chapter.getName());
        }
        map.put("chapters",chapterList);
        return map;
    }

    @Override
    public List<QuestionTypeAnalysisVo> getExamQuestionTypeAnalysis(Long scoreId) {
        return scoreMapper.getExamQuestionTypeAnalysis(scoreId);
    }

    @Override
    public List<QuestionTypeAnalysisVo> getExamQuestionDifficulty(Long scoreId) {
        return scoreMapper.getExamQuestionDifficulty(scoreId);
    }

    @Override
    public List<HashMap<String, Object>> getExamQuestionChapter(Long scoreId) {
        List<HashMap<String, Object>> examQuestionChapter = scoreDetailMapper.getExamQuestionChapter(scoreId);
        List<HashMap<String, Object>> result = new ArrayList<>();
        for (HashMap<String, Object> map : examQuestionChapter) {
            String chapterName = (String) map.get("chapterName");
            Long questionNum = (Long) map.get("questionNum");
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("name", chapterName);
            map2.put("value", questionNum);
            result.add(map2);
        }
        return result;
    }

    @Override
    public LeiDaTuVo getExamQuestionChapterScoreRate(Long scoreId) {

        List<HashMap<String, Double>> result = scoreDetailMapper.getExamQuestionChapterScoreRate(scoreId);
        List<HashMap<String, Object>> indicator = new ArrayList<>();
        List<Double> data = new ArrayList<>();
        for (HashMap<String, Double> map : result) {
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("text",map.get("chapterName"));
            map2.put("max",1);
            Double scoreRate = map.get("scoreRate");
            // 保留两位小数
            scoreRate = (double) (int) (scoreRate * 100) / 100;
            indicator.add(map2);
            data.add(scoreRate);
        }
        LeiDaTuVo leiDaTuVo = new LeiDaTuVo();
        leiDaTuVo.setIndicator(indicator);
        leiDaTuVo.setData(data);
        return leiDaTuVo;
    }

    @Override
    public List<KnowledgePointsAnalysisVo> getSubjectKnowledgePoint(Long subjectId,Long StudentId) {
        // 根据subjectId找到该科目的所有知识点信息
        List<KnowledgePointsAnalysisVo> knowledgePoints = knowledgePointsMapper.getSubjectKnowledgePoint(subjectId);
        // 根据subjectId和StudentId找到该学生该科目的所有成绩信息
        for (KnowledgePointsAnalysisVo knowledgePoint : knowledgePoints) {
            Long knowledgePointId = knowledgePoint.getId();
            HashMap<String, Object> knowledgePointScore =
                    scoreMapper.getSubjectKnowledgePoint(subjectId,StudentId,knowledgePointId);
            Long questionCount = (Long) knowledgePointScore.get("questionCount");
            BigDecimal wc = (BigDecimal) knowledgePointScore.get("wrongCount");
            Long wrongCount = 0L;
            if(wc!= null){
                wrongCount = wc.longValue();
            }
            Double scoreRate = (Double) knowledgePointScore.get("scoreRate");
            BigDecimal wr = (BigDecimal) knowledgePointScore.get("wrongRate");
            Double wrongRate = 0.0;
            if(wr!= null){
                wrongRate = wr.doubleValue();
                wrongRate = (double) (int) (wrongRate * 100) / 100;
            }
            // 保留两位小数
            if(scoreRate!= null) {
                scoreRate = (double) (int) (scoreRate * 100) / 100;
            }else {
                scoreRate = 0.0;
            }

            Double judge = questionCount * scoreRate;
            knowledgePoint.setQuestionCount(questionCount);
            knowledgePoint.setWrongCount(wrongCount);
            knowledgePoint.setScoreRate(scoreRate);
            knowledgePoint.setWrongRate(wrongRate);
            try {
                String s = knowledgeMasteryService.analyzeMasteryLevel(knowledgePoint.getQuestionCount(),
                        knowledgePoint.getScoreRate(), knowledgePoint.getWrongRate());
                knowledgePoint.setMasteryLevel(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            if(judge <5){
//                knowledgePoint.setMasteryLevel(MasteryLevelEnum.First);
//            }else if(judge < 10){
//                knowledgePoint.setMasteryLevel(MasteryLevelEnum.Second);
//            }else if(judge < 15){
//                knowledgePoint.setMasteryLevel(MasteryLevelEnum.Third);
//            }else if(judge < 20){
//                knowledgePoint.setMasteryLevel(MasteryLevelEnum.Fourth);
//            }else{
//                knowledgePoint.setMasteryLevel(MasteryLevelEnum.Fifth);
//            }

        }
        // 去除没有考过的知识点
        knowledgePoints.removeIf(next -> next.getQuestionCount() == 0);
        return knowledgePoints;
    }

    private void getCorrectRateByDifficulty(Chapter chapter, List<QuestionByChapterSumDto> questions, HashMap<String, Double> map, LevelEnum levelEnum) {
        // 统计该章节题目指定难度的总数量和正确数量
        int totalNum = 0;
        int correctNum = 0;
        // 遍历该章节题目信息
        for (QuestionByChapterSumDto question : questions) {
            if (question.getChapterId().equals(chapter.getId())) {
                if (question.getLevel().getCode().equals(levelEnum.getCode())) {
                    totalNum++;
                    if(question.getResult().getCode().equals("1")){
                        correctNum++;
                    }
                }
            }
        }

        // 计算该章节指定难度的正确率,保留两位小数
        double rate = (double) correctNum / totalNum;
        rate = (double) (int) (rate * 100) / 100;
        map.put(chapter.getName(), rate);
        // 保存试题总数
        String key = chapter.getName() +"-"+ levelEnum.getName();
        map.put(key, (double)totalNum);
    }


}
