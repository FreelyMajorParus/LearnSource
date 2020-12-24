package com.risesun.springclouduserservice.hystrix.strategy;

import com.risesun.springclouduserservice.hystrix.Hystrix;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Semaphore;

/**
 * @author admin
 */
@Component
public class SemaphoreHystrixInvoker extends AbstractHystrixInvoker {
    Semaphore semaphore = new Semaphore(10);

    @Override
    Object invoke(ProceedingJoinPoint proceedingJoinPoint, Method method, Hystrix annotation) {
        try {
            if (semaphore.tryAcquire()) {
                return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            } else {
                // 开启熔断状态
                statusManager.openHystrixStatus(method);
                // 执行fallback方法
                return invokeFallback(proceedingJoinPoint, method);
            }
        } catch (Throwable throwable) {
            // 开启熔断状态
            statusManager.openHystrixStatus(method);
            // 执行fallback方法
            return invokeFallback(proceedingJoinPoint, method);
        } finally {
            semaphore.release();
        }
    }
}
