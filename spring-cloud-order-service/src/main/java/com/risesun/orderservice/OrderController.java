package com.risesun.orderservice;

import com.risesun.IOrderService;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author admin
 */
@RestController
public class OrderController implements IOrderService {
    @Override
    public String listOrdersByUserId(Long userId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuilder result = new StringBuilder("userId:" + userId + ",\n orders=[");
        int orderCount = ThreadLocalRandom.current().nextInt(50);
        for (int i = 0; i < orderCount; i++) {
            result.append("orderId = " + ThreadLocalRandom.current().nextInt(1000) +"\n");
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public String insertOrder(Long userId) {
        int orderCount = ThreadLocalRandom.current().nextInt(50);
        return "insert success, cost:" + orderCount;
    }
}
