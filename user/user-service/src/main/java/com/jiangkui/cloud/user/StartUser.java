package com.jiangkui.cloud.user;

import com.jiangkui.cloud.core.utils.BeanManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

/**
 * package:    com.jiangkui.cloud
 * className:  StartUser
 * date:       2017/09/27 20:14
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StartUser {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(StartUser.class, args);
		BeanManager.setApplicationContext(applicationContext);
	}
}