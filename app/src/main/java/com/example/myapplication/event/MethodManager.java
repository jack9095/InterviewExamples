package com.example.myapplication.event;

import java.lang.reflect.Method;

public class MethodManager {
    private Class<?> type;
    private ThreadMode threadMode;
    private Method method;

    public MethodManager(Class<?> type, ThreadMode threadMode, Method method) {
        this.type = type;
        this.threadMode = threadMode;
        this.method = method;
    }

    public Class<?> getType() {
        return type;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public Method getMethod() {
        return method;
    }
}
