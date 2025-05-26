package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Paper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.*;

import java.util.List;

/**
* @author Administrator
* @description 针对表【paper】的数据库操作Service
* @createDate 2024-12-13 21:58:56
*/
public interface PaperService extends IService<Paper> {

    List<PaperVo> papers();

    IPage<PaperVo> papers(Page<PaperVo> paperVoPage);

    PaperDetailVo getPaperDetailById(Long paperId);

    TestQuestionDetailVo getAllQuestionDetail(Long paperId);

    List<TestQuestionVo> getQuestionByType(Long paperId);

    SortByTimePapersVo sortByTime();

    IPage<PaperVo> getPapersByStudentId(Page<PaperVo> paperVoPage, Long studentId,String key,String time);

    SortByTimePapersVo sortByTime(Long studentId);

    List<PaperVo> getPapersByStudentId(Long studentId,String key);

    ExamInfoVo getExamInfo(Long examId);

    void createExam(CreateExamVo createExamVo);
}
