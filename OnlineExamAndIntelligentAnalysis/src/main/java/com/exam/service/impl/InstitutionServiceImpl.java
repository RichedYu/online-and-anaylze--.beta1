package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.Institution;
import com.exam.service.InstitutionService;
import com.exam.mapper.InstitutionMapper;
import com.exam.vo.InstitutionInfoVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



/**
* @author Administrator
* @description 针对表【institution】的数据库操作Service实现
* @createDate 2024-12-13 21:58:56
*/
@Service
public class InstitutionServiceImpl extends ServiceImpl<InstitutionMapper, Institution>
    implements InstitutionService{

    @Resource
    private InstitutionMapper institutionMapper;

    @Override
    public IPage<InstitutionInfoVo> getInstitutionList(Page<InstitutionInfoVo> page,String keyword) {
        return institutionMapper.getInstitutionList(page, keyword);
    }

    @Override
    public Boolean createInstitution(String institutionName) {
        Institution institution = new Institution();
        institution.setName(institutionName);
        int insert = institutionMapper.insert(institution);

        return insert > 0;
    }
}




