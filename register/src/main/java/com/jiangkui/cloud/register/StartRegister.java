package com.jiangkui.cloud.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * package:    com.jiangkui.cloud
 * className:  StartRegister
 * date:       2017/09/27 20:48
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@SpringBootApplication
@EnableEurekaServer
public class StartRegister {
	public static void main(String[] args) {
		SpringApplication.run(StartRegister.class, args);
	}
}