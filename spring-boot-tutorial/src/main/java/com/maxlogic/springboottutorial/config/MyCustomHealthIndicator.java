package com.maxlogic.springboottutorial.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyCustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        System.out.println("MyCustomHealthIndicator is executed");
        return Health.up().build();
    }
}
