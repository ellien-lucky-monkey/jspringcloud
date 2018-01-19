package com.jiangkui.cloud.common;

import com.jiangkui.cloud.core.annotation.ExcludeComponentScan;
import com.jiangkui.cloud.core.utils.BeanManager;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * package:    com.jiangkui.cloud
 * className:  StartCommon
 * date:       2017/09/28 03:58
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@SpringCloudApplication//该注解所包含的相关注解有{@EnableDiscoveryClient@EnableCircuitBreaker@SpringBootApplication@SpringBootApplication}
@EnableFeignClients
@EnableHystrix
//@RibbonClient(name = "user-service", configuration = RibbonConfiguration.class)
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeComponentScan.class)})
public class StartCommon {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(StartCommon.class, args);
		BeanManager.setApplicationContext(null);
		BeanManager.setApplicationContext(applicationContext);
	}
}