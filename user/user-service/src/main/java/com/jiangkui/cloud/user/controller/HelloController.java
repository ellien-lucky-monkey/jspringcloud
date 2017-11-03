package com.jiangkui.cloud.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package:    com.jiangkui.cloud.controller
 * className:  HelloController
 * date:       2017/09/28 02:31
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@RestController
public class HelloController {
private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	@SuppressWarnings("all")
	@Autowired
	private CounterService counterService;

	@Autowired
	private DiscoveryClient client;

	@RequestMapping("/hello")
	public String hello() {
		ServiceInstance serviceInstance = client.getLocalServiceInstance();
		logger.info("[host:]-:" + serviceInstance.getHost()+"[port:]" + serviceInstance.getPort() + "[serviceId:]-" + serviceInstance.getServiceId());
		counterService.increment("hello count");
		return "Hello!";
	}
}