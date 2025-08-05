package com.onedimension.controller;

import com.onedimension.pojo.*;
import com.onedimension.service.ClazzService;
import com.onedimension.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzsController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 3.1 班级列表查询
     */
    @GetMapping
    public Result page(ClazzQueryParams clazzQueryParams) {
        log.info("获取班级列表: {}", clazzQueryParams);
        return ResultUtil.success(clazzService.page(clazzQueryParams));
    }

    /**
     * 3.2 删除班级
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级: {}", id);
        clazzService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 3.3 添加班级
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("新增班级: {}", clazz);
        clazzService.save(clazz);
        return ResultUtil.success();
    }

    /**
     * 3.4 根据ID查询
     */
    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Integer id) {
        log.info("根据id查询班级: {}", id);
        return ResultUtil.success(clazzService.getClazzById(id));
    }

    /**
     * 3.5 修改班级
     */
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("修改班级: {}", clazz);
        clazzService.update(clazz);
        return ResultUtil.success();
    }

    /**
     * 3.6 查询所有班级
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级");
        return ResultUtil.success(clazzService.list());
    }
}
