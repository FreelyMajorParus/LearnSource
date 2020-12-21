package com.risesun.orderservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
public class OrderConfigController {

    @Value("${heloo}")
    private String env;

    @GetMapping("/getConfig")
    public String getConfig(){
        return env;
    }
}
