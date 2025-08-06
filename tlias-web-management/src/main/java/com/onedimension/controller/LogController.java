package com.onedimension.controller;

import com.onedimension.pojo.PageParams;
import com.onedimension.pojo.Result;
import com.onedimension.service.LogService;
import com.onedimension.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/log")
@RestController
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    /**
     * 5.5 日志列表查询
     */
    @GetMapping("/page")
    public Result page(PageParams pageParams) {
        return ResultUtil.success(logService.page(pageParams));
    }

}
