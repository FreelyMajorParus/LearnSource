package com.risesun.orderservice.profileDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigMan {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("pro");
        context.register(EnvironmentAutoConfiguration.class);
        context.refresh();
        EnvironmentConfig bean = context.getBean(EnvironmentConfig.class);
        System.out.println(bean);
    }
}
