package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.*;

import java.util.List;

/**
* @author Administrator
* @description 针对表【paper】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.Paper
*/
public interface PaperMapper extends BaseMapper<Paper> {

    List<PaperVo> getAllPapers();

    IPage<PaperVo> getAllPapers(Page<PaperVo> paperVoPage);

    PaperDetailVo getPaperDetailById(Long paperId);

    List<TestQuestionVo> getQuestionNumScore(Long paperId);

    IPage<PaperVo> getAllPapersByStudentId(Page<PaperVo> paperVoPage, Long studentId,String key,String time);

    List<PaperVo> getAllPapersByStudentId(Long studentId,String key,String time);

    List<QuestionByTypeVo> getQuestionByType(Long examId);

    List<Answer> getAnswersByQuestionType(Long examId, String code);
}




