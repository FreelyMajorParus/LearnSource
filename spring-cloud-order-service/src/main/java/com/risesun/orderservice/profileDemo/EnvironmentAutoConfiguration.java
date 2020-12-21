package com.risesun.orderservice.profileDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author admin
 */
@Configuration
public class EnvironmentAutoConfiguration {
    @Bean
    @Profile("dev")
    public EnvironmentConfig devConfig(){
        return new EnvironmentConfig("dev");
    }
    @Bean
    @Profile("pro")
    public EnvironmentConfig proConfig(){
        return new EnvironmentConfig("pro");
    }
}
