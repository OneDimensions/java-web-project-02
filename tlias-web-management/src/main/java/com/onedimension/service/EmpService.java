package com.onedimension.service;

import com.onedimension.pojo.*;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParams empQueryParams);

    void saveEmp(Emp emp);

    void batchInsertEmpExpr(List<EmpExpr> empExprList);
}
