package com.jiangkui.cloud.common.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jiangkui.cloud.common.api.IDemoService;
import com.jiangkui.cloud.common.service.DemoService;
import com.jiangkui.cloud.user.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * package:    com.jiangkui.cloud.controller
 * className:  DemoController
 * date:       2017/09/28 04:17
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@RestController
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private DemoService demoService;
    @Autowired
    private LoadBalancerClient balancerClient;
    @Autowired
    private IDemoService IDemoService;

    @GetMapping("/demo/{id}")
    public User getUser(@PathVariable Long id) {
        ServiceInstance serviceInstance = balancerClient.choose("user-service");
        logger.info("[host:]-:" + serviceInstance.getHost() + "[port:]" + serviceInstance.getPort() + "[serviceId:]-" + serviceInstance.getServiceId());
        return demoService.findUserById(id);
    }

    @GetMapping("/demo1/{id}")
    public User findByIdAsync(@PathVariable Long id) throws ExecutionException, InterruptedException {
        ServiceInstance serviceInstance = balancerClient.choose("user-service");
        logger.info("[host:]-:" + serviceInstance.getHost() + "[port:]" + serviceInstance.getPort() + "[serviceId:]-" + serviceInstance.getServiceId());
        return demoService.findByIdAsync(id).get();
    }

    @GetMapping("/demo3")
    public List<User> findByIdsAsync(@RequestParam("ids") Long[] ids) throws ExecutionException, InterruptedException {
        ServiceInstance serviceInstance = balancerClient.choose("user-service");
        logger.info("[host:]-:" + serviceInstance.getHost() + "[port:]" + serviceInstance.getPort() + "[serviceId:]-" + serviceInstance.getServiceId());
        return demoService.findUsers(Arrays.asList(ids));
    }
//	@GetMapping("/demo2/{id}")
//	public User commandFind(@PathVariable Long id) throws ExecutionException, InterruptedException {
//		ServiceInstance serviceInstance = balancerClient.choose("user-service");
//		logger.info("[host:]-:" + serviceInstance.getHost()+"[port:]" + serviceInstance.getPort() + "[serviceId:]-" + serviceInstance.getServiceId());
//		return demoService.commandFind(id);
//	}

    @GetMapping("/collapser")
    public List<User> collapser() throws ExecutionException, InterruptedException {
        User user = demoService.findUserById(1L);
        User user1 = demoService.findUserById(2L);
        User user2 = demoService.findUserById(3L);
        List<User> users = Lists.newArrayList();
        users.add(user);
        users.add(user1);
        users.add(user2);
        return users;
    }
}