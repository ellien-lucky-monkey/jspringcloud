package com.jiangkui.cloud.gateway;

import com.jiangkui.cloud.gateway.base.AccessFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * package:    com.jiangkui.cloud.gateway
 * className:  StartGateWay
 * date:       2017/11/07 14:06
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@EnableZuulProxy
@SpringCloudApplication
public class StartGateWay {

	public static void main(String[] args) {
		new SpringApplicationBuilder(StartGateWay.class).web(true).run(args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}