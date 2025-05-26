package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.QuestionInfoVo;
import com.exam.vo.QuestionVo;
import com.exam.vo.questionType.JudgeVo;
import com.exam.vo.questionType.SingleVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【question】的数据库操作Service
* @createDate 2024-12-13 21:58:56
*/
public interface QuestionService extends IService<Question> {

    IPage<QuestionInfoVo> getQuestionList(Page<QuestionInfoVo> page, String keyword,Long subjectId,Long chapterId);

    IPage<QuestionVo> getWrongQuestion(Page<QuestionVo> pageParam, Long studentId, Long subjectId);

    boolean createSingle(SingleVo singleVo);

    boolean createJudge(JudgeVo judgeVo);

    boolean createFill(JudgeVo judgeVo);

    boolean createShortAnswer(JudgeVo judgeVo);

    boolean createMulti(SingleVo singleVo);

    boolean createApplication(JudgeVo judgeVo);
}
