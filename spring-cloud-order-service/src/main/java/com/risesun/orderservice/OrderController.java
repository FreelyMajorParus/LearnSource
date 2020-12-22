package com.risesun.orderservice;

//import com.risesun.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author admin
 */
@RestController
public class OrderController {
    @GetMapping("/orders")
    public String listOrdersByUserId(Long userId) {
        StringBuilder result = new StringBuilder("userId:" + userId + ", orders=[");
        int orderCount = ThreadLocalRandom.current().nextInt(50);
        for(int i = 0; i <orderCount ; i ++){
            result.append("orderId = " + ThreadLocalRandom.current().nextInt(1000));
        }
        result.append("]");
        return result.toString();
    }
}
