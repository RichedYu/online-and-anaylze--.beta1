package com.exam.mapper;

import com.exam.entity.MultiChoice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.MultiChoiceVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【multi_choice】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.MultiChoice
*/
public interface MultiChoiceMapper extends BaseMapper<MultiChoice> {

    List<MultiChoiceVo> getAllDetailByPaperId(Long paperId);
}




