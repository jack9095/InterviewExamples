package com.example.myapplication.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap 的数据结构，底层采用分段的数组+链表实现，线程安全
 * 通过把整个Map分为N个Segment，可以提供相同的线程安全，但是效率提升N倍，默认提升16倍。
 * (读操作不加锁，由于HashEntry的value变量是 volatile的，也能保证读取到最新的值。直接到主内存中拿数据)
 */
public class EventBus {

    static class InnerClass{
        static final EventBus INSTANCE = new EventBus();
    }
    private EventBus(){
        concurrentHashMap = new ConcurrentHashMap<>();
    }

    public static EventBus getInstance(){
        return InnerClass.INSTANCE;
    }

   private ConcurrentHashMap<Object, List<MethodManager>> concurrentHashMap;

    public void register(Object obj){
        final Method[] methods = obj.getClass().getMethods();
        List<MethodManager> lists = concurrentHashMap.get(obj);
        if (lists == null) {
            lists = new ArrayList<>();
        }
        MethodManager methodManager;
        for (Method method : methods) {
            boolean annotationPresent = method.isAnnotationPresent(Subscribe.class);
            if (annotationPresent) {
                Subscribe annotation = method.getAnnotation(Subscribe.class);
                methodManager = new MethodManager(method.getParameterTypes()[0],annotation.value(),method);
                lists.add(methodManager);
            }
        }

        // concurrentHashMap 使用 put() 方法的时候要使用锁
        concurrentHashMap.put(obj,lists);
    }

    public void unregister(Object obj){
        // concurrentHashMap 使用 remove() 方法的时候要使用锁
        concurrentHashMap.remove(obj);
    }

    public void post(Object o){
        Set<Object> objects = concurrentHashMap.keySet();
        for (Object obj: objects) {
            // concurrentHashMap 使用 get() 方法的时候没有使用锁
            List<MethodManager> methodManagers = concurrentHashMap.get(obj);
            if (methodManagers != null && !methodManagers.isEmpty()) {
                for (MethodManager manager: methodManagers) {
                    if (manager.getType().isAssignableFrom(o.getClass())) {
                        // invoke 方法，参数1 调用 方法的对象，参数2 调用 这个方法的传参
                        try {
                            manager.getMethod().invoke(obj,o);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void postStick(Object o){
        Set<Object> objects = concurrentHashMap.keySet();
        for (Object obj: objects) {
            // concurrentHashMap 使用 get() 方法的时候没有使用锁
            List<MethodManager> methodManagers = concurrentHashMap.get(obj); // 获取到被注解标记的方法清单集合
            if (methodManagers != null && !methodManagers.isEmpty()) {
                for (MethodManager manager: methodManagers) {
                    if (manager.getType().isAssignableFrom(o.getClass())) {
                        // invoke 方法，参数1 调用方法的对象，参数2 调用这个方法的传参
                        try {
                            manager.getMethod().invoke(obj,o);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
