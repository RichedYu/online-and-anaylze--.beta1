package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.QuestionInfoVo;
import com.exam.vo.QuestionVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【question】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.Question
*/
public interface QuestionMapper extends BaseMapper<Question> {

    IPage<QuestionInfoVo> getQuestionList(Page<QuestionInfoVo> page, String keyword,Long subjectId,Long chapterId);

    List<Question> getQuestionBySubjectIdAndType(Long subjectId, String code);

    IPage<QuestionVo> getWrongQuestion(Page<QuestionVo> pageParam, Long studentId, Long subjectId);
}




