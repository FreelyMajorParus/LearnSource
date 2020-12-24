package com.risesun.springclouduserservice.hystrix;

import com.risesun.springclouduserservice.hystrix.strategy.AbstractHystrixInvoker;
import com.risesun.springclouduserservice.hystrix.strategy.HystrixStrategy;
import com.risesun.springclouduserservice.hystrix.strategy.SemaphoreHystrixInvoker;
import com.risesun.springclouduserservice.hystrix.strategy.TimeoutHystrixInvoker;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * 断路器切面器
 * @author admin
 */
@Aspect
@Configuration
public class HystrixAspect {

    @Autowired
    StatusManager statusManager;

    @Autowired
    AbstractHystrixInvoker semaphoreHystrixInvoker;

    @Autowired
    AbstractHystrixInvoker timeoutHystrixInvoker;

    @Around("@annotation(com.risesun.springclouduserservice.hystrix.Hystrix)")
    public Object doCache(ProceedingJoinPoint pjpParam) throws Throwable {
        MethodSignature methodSignature = ((MethodSignature) pjpParam.getSignature());
        Method method = methodSignature.getMethod();
        Hystrix annotation = method.getAnnotation(Hystrix.class);
        HystrixState hystrixState = statusManager.getHystrixState(method);
        if (hystrixState.isOpen()) {
            String fallbackMethodName = annotation.fallback();
            assert StringUtils.isNotBlank(fallbackMethodName);
            // 找到fallback方法
            Method fallbackMethod = method.getDeclaringClass().getDeclaredMethod(annotation.fallback(), method.getParameterTypes());
            assert fallbackMethod != null;
            return timeoutHystrixInvoker.invokeFallback(pjpParam, method);
        } else {
            HystrixStrategy strategy = annotation.strategy();
            switch (strategy) {
                case timeout: return timeoutHystrixInvoker.invoke(pjpParam, method, annotation);
                case semaphore: return semaphoreHystrixInvoker.invoke(pjpParam, method, annotation);
                default: return timeoutHystrixInvoker.invoke(pjpParam, method, annotation);
            }
        }
    }
}
