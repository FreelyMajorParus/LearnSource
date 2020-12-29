package com.risesun.springclouduserservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.risesun.IOrderService;
import org.risesun.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 当localhost:8084/orders服务未启动，造成多次调用发生异常时，最终服务被熔断，即使满足num % 2 == 0,也会执行到fallback方法
 */
@RestController
public class HystrixController implements IUserService{

    @Autowired
    private IOrderService orderService;

    /**
     * HystrixCommandProperties
     * @param num
     * @return
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
    }, fallbackMethod = "fallback")
    @GetMapping("/hystrix/order/{num}")
    public String queryOrder(@PathVariable("num") int num) {
        if (num % 2 == 0) {
            System.out.println("正常访问");
            return "正常访问";
        }
        return orderService.listOrdersByUserId(1L);
    }

    /**
     * 返回兜底数据
     * @return
     */
    public String fallback(int num){
        return "error service, default response!";
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
    }, fallbackMethod = "timeoutFallback")
    @GetMapping("/hystrix/timeout")
    public String queryOrderTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "请求正常";
    }
    /**
     * 返回兜底数据
     * @return
     */
    public String timeoutFallback(){
        return "slow service, default response!";
    }

    @Override
    public String queryOrder(Long userId) {
        return orderService.listOrdersByUserId(1L);
    }

    @Override
    public String insertOrder() {
        return orderService.insertOrder(1L);
    }
}
