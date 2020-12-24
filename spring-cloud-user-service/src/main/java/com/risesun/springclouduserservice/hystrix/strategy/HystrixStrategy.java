package com.risesun.springclouduserservice.hystrix.strategy;

/**
 * 降级策略
 * @author admin
 */
public enum HystrixStrategy {

    /**
     * 超时降级
     */
    timeout,

    /**
     * 限流降级
     */
    semaphore

}
