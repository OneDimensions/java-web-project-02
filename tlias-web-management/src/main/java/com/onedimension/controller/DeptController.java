package com.onedimension.controller;

import com.onedimension.pojo.Dept;
import com.onedimension.pojo.Result;
import com.onedimension.service.DeptService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 获取部门列表
     */
    @GetMapping("/depts")
    public Result<List<Dept>> depts() {
        return deptService.getDepts();
    }

    /**
     * 删除部门
     * @param id 部门id
     */
    // 前端传递参数与服务端形参名一致, 可以不用写@RequestParam映射
    @DeleteMapping("/depts")
    public Result deleteDept(Integer id) {
        return deptService.deleteDept(id);
    }

    /**
     * 新增部门
     */
    @PostMapping("/depts")
    public Result addDept(Dept dept) {
        System.out.println(dept);
        return deptService.addDept(dept);
    }

}
