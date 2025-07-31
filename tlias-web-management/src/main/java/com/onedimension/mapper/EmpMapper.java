package com.onedimension.mapper;

import com.onedimension.pojo.Emp;
import com.onedimension.pojo.EmpQueryParams;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    // -----原始分页实现
    // List<Emp> page(Integer start, Integer pageSize);
    //
    // /**
    //  * 获取总数
    //  */
    // @Select("select count(*) from emp e left join dept d on e.dept_id = d.id;")
    // Long total();

    // -----mybatis分页实现 pagehelper

    /**
     * 员工信息分页
     */
    List<Emp> page(EmpQueryParams empQueryParams);

    /**
     * 新增员工
     */
    // Options注解可以获取到返回的主键, useGeneratedKeys: 表示使用生成的主键, keyProperty: 表示返回的主键名
    // @Options(useGeneratedKeys = true, keyProperty = "id")  写在了xml中
    Integer saveEmp(Emp emp);

    /**
     * 删除员工
     */
    void deleteEmpById(List<Integer> ids);
}
