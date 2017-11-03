//package com.jiangkui.cloud.common.command;
//
//import com.jiangkui.cloud.user.User;
//import com.netflix.hystrix.HystrixCommandGroupKey;
//import com.netflix.hystrix.HystrixCommandKey;
//import com.netflix.hystrix.HystrixObservableCommand;
//import org.springframework.web.client.RestTemplate;
//import rx.Observable;
//
///**
// * package:    com.jiangkui.cloud.common.command
// * className:  UserObservableCommand
// * date:       2017/10/11 17:36
// * author      jiangkui  😛😛😛😛😛  (o>=<o)
// * description
// */
//public class UserObservableCommand extends HystrixObservableCommand<User> {
//
//	private RestTemplate restTemplate;
//	private Long id;
//
//	protected UserObservableCommand(RestTemplate restTemplate, Long id) {
//		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup"))
//		.andCommandKey(HystrixCommandKey.Factory.asKey("UserObservableCommand")));
//		this.restTemplate = restTemplate;
//		this.id = id;
//	}
//
//	@Override
//	protected Observable<User> construct() {
//		return Observable.create(subscriber -> {
//			if (!subscriber.isUnsubscribed()) {
//				try {
//					User user = restTemplate.getForObject("http://user-service/user/" + id, User.class);
//					subscriber.onNext(user);
//					subscriber.onCompleted();
//				}
//				catch (Exception e) {
//					subscriber.onError(e);
//				}
//
//			}
//		});
//	}
//
//	@Override
//	protected String getCacheKey() {
//		return String.valueOf(id);
//	}
//}