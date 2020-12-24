package com.risesun.springclouduserservice.hystrix.strategy;

import com.risesun.springclouduserservice.hystrix.Hystrix;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@Component
public class TimeoutHystrixInvoker extends AbstractHystrixInvoker{
    @Override
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint, Method method, Hystrix annotation) {
        FutureTask<Object> futureTask = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                throw new IllegalStateException();
            }
        });
        threadPoolExecutor.submit(futureTask);
        try {
            Object result = futureTask.get(annotation.timeout(), TimeUnit.SECONDS);
            if(futureTask.isDone()){
                return result;
            }
            throw new IllegalStateException();
        } catch (Exception e) {
            // 开启熔断状态
            statusManager.openHystrixStatus(method);
            // 执行fallback方法
            return invokeFallback(proceedingJoinPoint, method);
        }
    }
}
