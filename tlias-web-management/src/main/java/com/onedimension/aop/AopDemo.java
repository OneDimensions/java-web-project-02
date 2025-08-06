package com.onedimension.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect // 标识这是一个aop切面类
@Component // 将这个类交给spring管理,spring会生成一个代理对象的bean
// @Order(1) // 指定切面运行的优先级
public class AopDemo {

    // 标识这是一个切入点表达式, 将公共的切入点表达式封装起来  公共切入点表达式, 其他切面类也能用
    @Pointcut("execution(* com.onedimension.service.impl.*.*(..))")
    public void pointcut() {}

    // 切入点表达式的匹配形式
    // execution(返回值类型 包名.类名.方法名(参数类型)): 根据方法的签名匹配
    //   * com.onedimension.service.impl.*.*(..))
    // @annotation: 根据注解切入点表达式
    // @annotation(com.onedimension.annotation.Log): 只要是有这个注解的方法都会被切入

    // 标识这是一个前置通知: 在目标方法执行之前执行
    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

    // @After() : 在目标方法执行之后执行, 无论目标方法是否抛出异常都会执行
    // @AfterReturning: 返回后通知, 在目标方法执行之后执行, 只有目标方法没有抛出异常时才会执行
    // @AfterThrowing: 异常后通知, 在目标方法执行异常之后执行

    // 标识这是一个环绕通知 在目标方法执行之前和之后都执行
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = pjp.proceed();// 执行目标方法 获取返回值
        System.out.println("around: " + proceed);
        return proceed;
    }

}
