package com.onedimension.utils;

import org.springframework.stereotype.Component;

// 线程局部变量
// 用于保存当前登录用户的id
// 此变量只在当前线程有效
public class ThreadLocalUtil {
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Integer id) {
        threadLocal.set(id);
    }

    public static Integer getCurrentId() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
