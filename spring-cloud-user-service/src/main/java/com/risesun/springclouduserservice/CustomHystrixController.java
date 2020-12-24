package com.risesun.springclouduserservice;

import com.risesun.springclouduserservice.hystrix.Hystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
public class CustomHystrixController {

    @Hystrix(fallback = "timeoutRollback")
    @GetMapping("/custom/timeout")
    public String queryOrderTimeout(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "请求正常";
    }

    public String timeoutRollback(){
        return "请求熔断";
    }
}
