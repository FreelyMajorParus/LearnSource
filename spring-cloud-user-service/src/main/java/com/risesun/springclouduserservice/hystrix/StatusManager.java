package com.risesun.springclouduserservice.hystrix;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 断路器状态管理器
 * @author admin
 */
@Component
public class StatusManager {
    ConcurrentHashMap<String, HystrixState> statusMap = new ConcurrentHashMap<>();

    public HystrixState getHystrixState(Method method){
        String key = initStateKey(method);
        return statusMap.containsKey(key) ? statusMap.get(key) : HystrixState.init();
    }

    private String initStateKey(Method method) {
        StringBuilder sb = new StringBuilder(method.getDeclaringClass().getName());
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            sb.append(parameterType.getSimpleName());
            sb.append("#");
        }
        return sb.toString();
    }

    public void openHystrixStatus(Method method){
        String key = initStateKey(method);
        if (statusMap.containsKey(key)) {
            HystrixState hystrixState = statusMap.get(key);
            hystrixState.open();
        }else{
            HystrixState state = HystrixState.init();
            state.open();
            statusMap.put(key, state);
        }
    }
}
