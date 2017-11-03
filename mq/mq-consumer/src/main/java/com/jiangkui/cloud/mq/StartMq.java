package com.jiangkui.cloud.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * package:    PACKAGE_NAME
 * className:  com.jiangkui.cloud.mq.StartMq
 * date:       2017/10/09 14:09
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StartMq {
	public static void main(String[] args) {
		SpringApplication.run(StartMq.class, args);
	}
}