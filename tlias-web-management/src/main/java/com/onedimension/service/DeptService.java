package com.onedimension.service;

import com.onedimension.pojo.Dept;
import com.onedimension.pojo.Result;

import java.util.List;

public interface DeptService {

    /**
     * 获取部门列表
     *
     * @return 部门列表
     */
    Result<List<Dept>> getDepts();

    /**
     * 删除部门
     */
    Result deleteDept(Integer id);

    Result addDept(Dept dept);

    Result<Dept> getDeptById(Integer id);

    Result updateDept(Dept dept);
}
