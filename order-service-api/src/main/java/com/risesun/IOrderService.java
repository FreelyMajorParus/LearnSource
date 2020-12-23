package com.risesun;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="order-service", fallback = IOrderService.OrderServiceFallback.class)
public interface IOrderService {
    @RequestMapping("listOrdersByUserId")
    String listOrdersByUserId(Long userId);

    @RequestMapping("insertOrder")
    String insertOrder(Long userId);

    /* openFeign 继承 hystrix*/
    @Component
    class OrderServiceFallback implements IOrderService{
        @Override
        public String listOrdersByUserId(Long userId) {
            return "query order waiting for later retry!";
        }

        @Override
        public String insertOrder(Long userId) {
            return "insert order waiting for later retry!";
        }
    }
}
