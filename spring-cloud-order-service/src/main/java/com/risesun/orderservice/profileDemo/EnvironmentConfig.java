package com.risesun.orderservice.profileDemo;

public class EnvironmentConfig {
    private String env;

    public EnvironmentConfig(String env) {
        this.env = env;
    }

    @Override
    public String toString() {
        return "EnvironementConfig{" +
                "env='" + env + '\'' +
                '}';
    }
}
