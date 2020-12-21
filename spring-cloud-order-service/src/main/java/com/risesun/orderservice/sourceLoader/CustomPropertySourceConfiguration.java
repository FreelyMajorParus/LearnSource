package com.risesun.orderservice.sourceLoader;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;

//@Configuration
public class CustomPropertySourceConfiguration {
//    @Bean
    public PropertySourceLocator jsonPropertySourceLoader(){
        return new JsonPropertySourceLocator();
    }
}
