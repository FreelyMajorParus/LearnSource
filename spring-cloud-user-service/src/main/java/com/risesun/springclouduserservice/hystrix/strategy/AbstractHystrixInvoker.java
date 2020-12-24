package com.risesun.springclouduserservice.hystrix.strategy;


import com.netflix.discovery.converters.Auto;
import com.risesun.springclouduserservice.hystrix.Hystrix;
import com.risesun.springclouduserservice.hystrix.StatusManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @author admin
 */
public abstract class AbstractHystrixInvoker {

    @Autowired
    protected StatusManager statusManager;

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,100, 5000, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(1000));

    public Object invokeFallback(ProceedingJoinPoint proceedingJoinPoint, Method method) {
        try {
            Hystrix annotation = method.getAnnotation(Hystrix.class);
            String fallback = annotation.fallback();
            Method fallBackMethod = method.getDeclaringClass().getMethod(fallback, method.getParameterTypes());
            return fallBackMethod.invoke(proceedingJoinPoint.getTarget(), proceedingJoinPoint.getArgs());
        } catch (IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public abstract Object invoke(ProceedingJoinPoint proceedingJoinPoint, Method method, Hystrix annotation);
}
