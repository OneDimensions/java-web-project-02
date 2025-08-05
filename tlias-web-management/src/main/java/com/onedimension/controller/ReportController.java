package com.onedimension.controller;

import com.onedimension.pojo.Result;
import com.onedimension.service.ReportService;
import com.onedimension.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 5.2 员工职位人数统计
     */
    @GetMapping("/empJobData")
    public Result empJobData() {
        return ResultUtil.success(reportService.getEmpJobData());
    }

    /**
     * 5.1 员工性别统计
     */
    @GetMapping("/empGenderData")
    public Result empGenderData() {
        return ResultUtil.success(reportService.getEmpGenderData());
    }

    /**
     * 5.3 学员学历统计
     */
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        return ResultUtil.success(reportService.getStudentDegreeData());
    }

    /**
     * 5.4 班级人数统计
     */
    @GetMapping("/studentCountData")
    public Result studentCountData() {
        return ResultUtil.success(reportService.getStudentCountData());
    }
}
