package com.onedimension.service;

import com.onedimension.pojo.EmpLog;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface EmpLogService {

    // 无论插入员工的事务执行结果是成功还是失败,都需要插入日志
    // 所以这里控制事务的传播行为 为 REQUIRED
    // 代表创建一个新的事务, 挂起当前事务
    // 无论当前有没有事务, 其他事务回滚不会影响到这个事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertLog(EmpLog empLog);

}
