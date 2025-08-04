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

    @GetMapping("/empJobData")
    public Result empJobData() {
        return ResultUtil.success(reportService.empJobData());
    }

}
