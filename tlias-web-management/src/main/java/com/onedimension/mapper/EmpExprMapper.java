package com.onedimension.mapper;

import com.onedimension.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工工作经历
     */
    Integer batchInsertEmpExpr(List<EmpExpr> empExprList);
}
