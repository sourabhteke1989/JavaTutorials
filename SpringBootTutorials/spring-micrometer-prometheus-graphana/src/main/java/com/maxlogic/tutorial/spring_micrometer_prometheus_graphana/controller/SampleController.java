package com.maxlogic.tutorial.spring_micrometer_prometheus_graphana.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private final Counter helloCounter;
    private final Timer helloTimer;

    public SampleController(MeterRegistry meterRegistry) {
        this.helloCounter = meterRegistry.counter("hello_requests_total");
        this.helloTimer = Timer.builder("hello_requests_timer")
                // .publishPercentiles(0.5, 0.95)
                .serviceLevelObjectives(Duration.ofMillis(10), Duration.ofMillis(25), Duration.ofMillis(50),
                        Duration.ofMillis(100), Duration.ofMillis(200), Duration.ofMillis(500), Duration.ofSeconds(1),
                        Duration.ofSeconds(2), Duration.ofSeconds(5), Duration.ofSeconds(10))
                // .publishPercentileHistogram()
                .register(meterRegistry);
    }

    @GetMapping("/hello")
    public String hello() {
        helloCounter.increment();
        return helloTimer.record(() -> {
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, World!";
        });
    }
}
