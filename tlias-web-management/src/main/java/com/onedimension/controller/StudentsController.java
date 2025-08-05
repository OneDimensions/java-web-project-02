package com.onedimension.controller;

import com.onedimension.pojo.Result;
import com.onedimension.pojo.Student;
import com.onedimension.pojo.StudentsQueryParams;
import com.onedimension.service.StudentsService;
import com.onedimension.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    /**
     * 4.1 学员列表查询
     */
    @GetMapping
    public Result page(StudentsQueryParams studentsQueryParams) {
        log.info("分页查询: {}", studentsQueryParams);
        return ResultUtil.success(studentsService.page(studentsQueryParams));
    }

    /**
     * 4.3 添加学员
     */
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学员: {}", student);
        studentsService.save(student);
        return ResultUtil.success();
    }

    /**
     * 4.2 删除学员
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学员: {}", ids);
        studentsService.delete(ids);
        return ResultUtil.success();
    }

    /**
     * 4.4 根据ID查询
     */
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable Integer id) {
        log.info("根据id查询学员: {}", id);
        return ResultUtil.success(studentsService.getStudentById(id));
    }

    /**
     * 4.5 修改学员
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员: {}", student);
        studentsService.update(student);
        return ResultUtil.success();
    }

    /**
     * 4.6 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Short score) {
        log.info("违纪处理: {}, {}", id, score);
        studentsService.violation(id, score);
        return ResultUtil.success();
    }
}
