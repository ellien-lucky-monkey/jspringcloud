package com.jiangkui.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * @author ellien
 * @date 2018/01/23 11:40
 */
@SpringBootApplication
@EnableZipkinServer
public class StartZipkinServer {
    public static void main(String[] args) {
        SpringApplication.run(StartZipkinServer.class, args);
    }
}
