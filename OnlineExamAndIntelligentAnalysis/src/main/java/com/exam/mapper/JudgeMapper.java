package com.exam.mapper;

import com.exam.entity.Judge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.JudgeVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【judge】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.Judge
*/
public interface JudgeMapper extends BaseMapper<Judge> {

    List<JudgeVo> getAllDetailByPaperId(Long paperId);
}




