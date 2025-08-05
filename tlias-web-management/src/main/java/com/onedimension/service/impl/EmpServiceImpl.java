package com.onedimension.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onedimension.mapper.EmpExprMapper;
import com.onedimension.mapper.EmpMapper;
import com.onedimension.pojo.*;
import com.onedimension.service.EmpLogService;
import com.onedimension.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    /**
     * 传统分页查询方法
     */
    // @Override
    // public PageResult<Emp> page(@RequestParam(defaultValue = "1") Integer page,
    //                             @RequestParam(defaultValue = "10") Integer pageSize) {
    //     int start = (page - 1) * pageSize;
    //     List<Emp> emps = empMapper.page(start, pageSize);
    //     Long total = empMapper.total();
    //     PageResult<Emp> pageResult = new PageResult<Emp>(total, emps);
    //     return pageResult;
    // }
    @Override
    public PageResult<Emp> page(EmpQueryParams empQueryParams) {
        /**
         * pagehelper注意事项
         * 1. sql结尾不能有分号
         * 2. pagehelper仅能对紧跟在其后面的第一个查询语句进行分页处理
         */
        log.info("canshu=====: {}", empQueryParams);
        // 1. 设置分页参数
        PageHelper.startPage(empQueryParams.getPage(), empQueryParams.getPageSize());
        // 2. 执行查询 PageHelper第一个查询语句
        List<Emp> empList = empMapper.page(empQueryParams);

        // 3. 封装结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    // 事务: 一组操作要么都成功,要么都失败, 保证数据的一致性
    // 事务的注解要加在service层
    // start transaction
    // 成功了就commit提交, 失败了就rollback回滚

    // @Transactional: 开启事务注解 这个注解默认是出现运行时异常RuntimeException才会回滚
    // (rollbackFor: 指定出现哪些异常就回滚)

    // (propagation: 事务的传播行为)
    // REQUIRED: 如果当前存在事务,则加入该事务, 如果当前没有事务, 则创建一个新的事务( 默认 )
    // REQUIRES_NEW: 创建一个新的事务, 如果当前存在事务, 则把当前事务挂起
    // NESTED: 如果当前存在事务,则在嵌套事务中执行, 如果当前没有事务,则创建一个新的事务

    // 事务的四大特性
    // 1. 原子性: 事务是最小的操作单元, 一组操作要么都成功,要么都失败, 保证数据的一致性
    // 2. 一致性: 事务完成时, 必须使所有的数据都一致
    // 3. 隔离性: 数据库系统提供的隔离机制, 保证事务不受外部并发影响的独立环境下运行
    // 4. 持久性: 事务一旦提交或者回滚, 这个对数据的操作就是永久性的

    @Transactional(rollbackFor = {Exception.class})
    public void saveEmp(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.saveEmp(emp);


            List<EmpExpr> empExprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(empExprList)) {
                empExprList.forEach(e -> {
                    // 此处设置的员工id 是新增员工时使用@options注解获取的主键返回
                    e.setEmpId(emp.getId());

                });
                batchInsertEmpExpr(empExprList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 记录日志
            // 因为开启了默认事务, 并且默认insertLog的事务是被当前事务所控制
            // 如果上面出现了异常, 这个数据库插入就会被回滚
            // 而目的是要无论是成功还是失败都记录日志, 写入到数据库
            // 所以需要控制事务传播行为, 让insertLog开启新的事务
            EmpLog empLog = new EmpLog();
            empLog.setInfo("新增员工" + emp);
            empLog.setOperateTime(LocalDateTime.now());
            empLogService.insertLog(empLog);
        }
    }

    @Override
    public void batchInsertEmpExpr(List<EmpExpr> empExprList) {
        empExprMapper.batchInsertEmpExpr(empExprList);
    }

    @Override
    @Transactional()
    public void deleteEmp(List<Integer> ids) {
        empMapper.deleteEmpById(ids);
        empExprMapper.deleteEmpExprByEmpId(ids);
    }

    @Override
    public Emp getEmpById(Integer id) {
        // 使用两次查询复制
        // Emp emp = empMapper.getEmpById(id);
        // emp.setExprList(empExprMapper.getEmpExprByEmpId(id));
        Emp emp = empMapper.getEmpByIdV2(id);
        return emp;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void modify(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        // 1. 更新基本信息
        empMapper.updateEmpById(emp);

        // 2. 批量更新员工经历(先删再插入)
        // 先删除
        // 获取员工ID列表
        empExprMapper.deleteEmpExprByEmpId(Arrays.asList(emp.getId()));

        // 再插入
        List<EmpExpr> exprList = emp.getExprList();

        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(e -> {
                // 设置员工id
                e.setEmpId(emp.getId());
            });
            empExprMapper.batchInsertEmpExpr(exprList);
        }

    }

    @Override
    public List<Emp> list() {
        return empMapper.page(null);
    }
}
