package com.onedimension.service;

import com.onedimension.pojo.Emp;
import com.onedimension.pojo.EmpQueryParams;
import com.onedimension.pojo.PageResult;
import com.onedimension.pojo.Result;

import java.time.LocalDate;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParams empQueryParams);

    Result add(Emp emp);
}
