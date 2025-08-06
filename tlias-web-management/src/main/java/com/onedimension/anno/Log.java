package com.onedimension.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// 此注解用于标识切面,加了这个注解的方法会被切面拦截
// 记录日志
public @interface Log {
}
