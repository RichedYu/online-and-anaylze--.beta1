package com.exam.service;

import com.exam.entity.PaperQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【paper_question】的数据库操作Service
* @createDate 2024-12-13 21:58:56
*/
public interface PaperQuestionService extends IService<PaperQuestion> {

    int queryQuestionNum(Long paperId);
}
