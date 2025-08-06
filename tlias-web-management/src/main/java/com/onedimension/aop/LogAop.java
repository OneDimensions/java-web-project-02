package com.onedimension.aop;

import com.onedimension.mapper.OperateLogMapper;
import com.onedimension.pojo.OperateLog;
import com.onedimension.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAop {

    private final OperateLogMapper operateLogMapper;

    public LogAop(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    @Around("@annotation(com.onedimension.anno.Log)")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        // 操作耗时
        long startTime = System.currentTimeMillis();
        // 执行目标方法
        Object proceed = pjp.proceed();// 执行目标方法 获取返回值
        long endTime = System.currentTimeMillis();

        // 构建操作日志实体
        OperateLog operateLog = new OperateLog();
        Integer userId = ThreadLocalUtil.getCurrentId();// 操作人id
        log.info("userId: {}", userId);
        if (userId != null) {
            operateLog.setOperateEmpId(userId);
        }
        operateLog.setOperateTime(LocalDateTime.now().toString()); // 操作时间
        operateLog.setClassName(pjp.getTarget().getClass().getName()); // 操作的类名
        operateLog.setMethodName(pjp.getSignature().getName()); // 操作的方法名
        operateLog.setMethodParams(Arrays.toString(pjp.getArgs()));// 操作的方法参数
        operateLog.setReturnValue(proceed != null ? proceed.toString() : "void");
        operateLog.setCostTime(endTime - startTime); // 操作耗时
        operateLogMapper.insert(operateLog);
        return proceed;
    }
}
