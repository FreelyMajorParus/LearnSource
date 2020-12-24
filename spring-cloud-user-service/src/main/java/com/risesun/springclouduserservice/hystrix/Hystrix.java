package com.risesun.springclouduserservice.hystrix;

import com.risesun.springclouduserservice.hystrix.strategy.HystrixStrategy;

import java.lang.annotation.*;

/**
 * @author risesun
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Hystrix {

    HystrixStrategy strategy() default HystrixStrategy.timeout;

    int timeout() default 3;

    int semaphore() default 10;

    String fallback();
}
