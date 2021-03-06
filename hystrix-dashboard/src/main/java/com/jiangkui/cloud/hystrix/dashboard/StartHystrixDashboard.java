package com.jiangkui.cloud.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@SpringBootApplication
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableCircuitBreaker
public class StartHystrixDashboard {
    public static void main(String[] args) {
       SpringApplication.run(StartHystrixDashboard.class, args);
    }
}
