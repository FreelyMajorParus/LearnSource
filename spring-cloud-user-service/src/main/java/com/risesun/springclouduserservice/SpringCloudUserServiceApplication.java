package com.risesun.springclouduserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableHystrix
public class SpringCloudUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudUserServiceApplication.class, args);
    }

}
