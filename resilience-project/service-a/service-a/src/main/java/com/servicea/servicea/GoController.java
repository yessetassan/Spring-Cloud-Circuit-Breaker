package com.servicea.servicea;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping
@Slf4j
public class GoController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String base_url = "http://localhost:8081";
    private static final String service_name = "backend_a";

    private static int attempts = 0;
    @GetMapping
//    @CircuitBreaker(name = service_name,fallbackMethod = "fall")
//    @Retry(name = service_name)
    @RateLimiter(name = service_name)
    public CompletableFuture<String> all() {
        log.info("{} attempt", ++attempts);
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject(
                base_url,
                String.class)
        );
    }

    public CompletableFuture<String> fall(Exception exception) {
        return CompletableFuture.supplyAsync(() -> "Such Many Requests !!!");
    }

}
