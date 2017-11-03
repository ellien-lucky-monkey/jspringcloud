package com.jiangkui.cloud.common;

import com.jiangkui.cloud.core.annotation.ExcludeComponentScan;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * package:    com.jiangkui.cloud.user.config
 * className:  RibbonConfiguration
 * date:       2017/10/11 13:56
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 * <p>修改ribbon的默认负载均衡规则 <br/>
 *      注：该类不能处于主类的@ComponentScan扫描之下,需要在主类中过滤掉通过自定义注解@ExcludeComponentScan</p>
 */
@Configuration
@ExcludeComponentScan
public class RibbonConfiguration {
	@Bean
	public IRule ribbonRule() {
		//负载均衡规则修改为随机，默认是轮询模式
		return new RandomRule();
	}
}