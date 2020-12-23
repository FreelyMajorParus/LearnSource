package com.risesun;

import org.springframework.web.bind.annotation.RequestMapping;

public interface IOrderService {
    @RequestMapping("listOrdersByUserId")
    String listOrdersByUserId(Long userId);

    @RequestMapping("insertOrder")
    String insertOrder(Long userId);
}
