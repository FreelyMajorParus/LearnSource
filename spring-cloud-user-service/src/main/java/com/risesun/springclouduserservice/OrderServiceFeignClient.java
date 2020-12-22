package com.risesun.springclouduserservice;

import com.risesun.IOrderService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="order-service")
public interface OrderServiceFeignClient extends IOrderService {
}
