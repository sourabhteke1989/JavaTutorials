package com.maxlogic.tutorial.spring_micrometer_prometheus_graphana.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private final Counter helloCounter;
    private final Timer helloTimer;

    public SampleController(MeterRegistry meterRegistry) {
        this.helloCounter = meterRegistry.counter("hello_requests_total");
        this.helloTimer = meterRegistry.timer("hello_requests_timer");
    }

    @GetMapping("/hello")
    public String hello() {
        helloCounter.increment();
        return helloTimer.record(() -> {
            return "Hello, World!";
        });
    }
}
