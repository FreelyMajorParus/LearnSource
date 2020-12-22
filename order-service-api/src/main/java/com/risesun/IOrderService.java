package com.risesun;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1")
public interface IOrderService {
    @RequestMapping("listOrdersByUserId")
    String listOrdersByUserId(Long userId);
}
