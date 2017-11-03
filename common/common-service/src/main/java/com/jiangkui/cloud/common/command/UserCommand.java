//package com.jiangkui.cloud.common.command;
//
//import com.jiangkui.cloud.user.User;
//import com.netflix.hystrix.HystrixCommand;
//import com.netflix.hystrix.HystrixCommandGroupKey;
//import com.netflix.hystrix.HystrixCommandKey;
//import com.netflix.hystrix.HystrixThreadPoolKey;
//import org.springframework.web.client.RestTemplate;
//
///**
// * package:    com.jiangkui.cloud.common.command
// * className:  UserCommand
// * date:       2017/10/11 17:09
// * author      jiangkui  😛😛😛😛😛  (o>=<o)
// * description
// */
//public class UserCommand extends HystrixCommand<User> {
//
//	private RestTemplate restTemplate;
//	private Long id;
//
//	public UserCommand(RestTemplate restTemplate, Long id) {
//		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup"))
//				.andCommandKey(HystrixCommandKey.Factory.asKey("UserCommand"))
//				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserThreadPool")));
//		this.restTemplate = restTemplate;
//		this.id = id;
//	}
//
//	@Override
//	protected User run() throws Exception {
//		return this.restTemplate.getForObject("http://user-service/user/" + id, User.class);
//	}
//
//	@Override
//	protected User getFallback() {
//		return new User();
//	}
//
//	@Override
//	protected String getCacheKey() {
//		return String.valueOf(id);
//	}
//}