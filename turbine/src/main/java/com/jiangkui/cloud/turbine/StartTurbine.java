package com.jiangkui.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author ellien
 * @date 2018/01/22 19:10
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
public class StartTurbine {
    public static void main(String[] args) {
        SpringApplication.run(StartTurbine.class, args);
    }
}
