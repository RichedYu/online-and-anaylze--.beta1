package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Major;
import com.exam.entity.Subject;
import com.exam.service.MajorService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.InstitutionInfoVo;
import com.exam.vo.MajorInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Author:CM
 * Date: 2025/1/21 16:52
 */
@Tag(name = "专业管理")
@RestController
@RequestMapping("/exam")
public class MajorController {

    @Resource
    private MajorService majorService;

    // 得到所有专业信息
    @Operation(summary = "得到所有专业信息")
    @GetMapping("/majorList")
    public Result<List<Major>> getMajorList() {
        System.out.println("正在调用service层方法获得所有专业信息-");
        List<Major> list = majorService.list();
        return Result.ok(list);
    }

    // 获得专业列表(分页)专业编号、专业名称、班级数量、学生人数、创建时间、更新时间
    @Operation(summary = "获得专业列表")
    @GetMapping("/majorList/{current}/{size}/{keyword}")
    public Result<IPage<MajorInfoVo>> getMajorList(@PathVariable("current") Integer current,
                                                         @PathVariable("size") Integer size,
                                                         @PathVariable(value = "keyword",required = false) String keyword) {
        System.out.println("正在调用service层方法获得分页数据-----------");
        assert current != null;
        assert size != null;
        // 调用service层方法获得分页数据
        Page<MajorInfoVo> page = new Page<>(current, size);
        IPage<MajorInfoVo> majorInfoVos = majorService.getMajorList(page, keyword);
        return Result.ok(majorInfoVos);
    }

    // 新增专业
    @Operation(summary = "新增专业")
    @PostMapping("/createMajor")
    public Result<String> createMajor(@RequestBody Major major) {
        System.out.println("正在调用service层方法新增专业-");
//        // 去掉学院名称两边的双引号
//        institutionName = institutionName.replaceAll("^\"|\"$", "");

        // 数据校验
        System.out.println(major.toString());
        if(major == null){
            System.out.println("major类对象为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        if(major.getName() == null || major.getName().trim().equals("")){
            System.out.println("专业名称不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        if (major.getInstitutionId() == null) {
            System.out.println("学院ID不能为空-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        // 查找数据库是否存在该专业
        LambdaQueryWrapper<Major> wr = new LambdaQueryWrapper<>();
        wr.eq(Major::getName, major.getName());
        wr.eq(Major::getInstitutionId, major.getInstitutionId());
        Major major1 = majorService.getOne(wr);
        if (major1 != null) {
            System.out.println("该专业已存在-----------正在返回失败信息-");
            return Result.fail(ResultCodeEnum.INSERT_ERROR);
        }
        System.out.println("数据校验通过-----------正在调用service层方法保存数据-----------");
        boolean flag = majorService.save(major);
        if (flag) {
            System.out.println("保存数据成功-----------正在返回成功信息-");
            return Result.ok("创建成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 删除专业
    @Operation(summary = "删除专业")
    @DeleteMapping("/deleteMajor/{majorId}")
    public Result<String> deleteInstitution(@PathVariable("majorId") Long majorId) {
        System.out.println("正在调用service层方法删除学院-");
        if (majorId == null){
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        boolean flag = majorService.removeById(majorId);
        if (flag) {
            return Result.ok("删除成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

}
