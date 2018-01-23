package com.jiangkui.cloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.Serializable;

/**
 * @author ellien
 * @date 2018/01/19 15:54
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StartConfigClient {
    public static void main(String[] args) {
        SpringApplication.run(StartConfigClient.class, args);
    }
}
