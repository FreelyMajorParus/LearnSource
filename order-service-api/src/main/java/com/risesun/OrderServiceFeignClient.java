package com.risesun;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value="order-service", fallback = OrderServiceFeignClient.OrderServiceFallback.class)
public interface OrderServiceFeignClient extends IOrderService {
    /* openFeign 继承 hystrix*/
    @Component
    class OrderServiceFallback implements OrderServiceFeignClient{
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