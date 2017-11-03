package com.jiangkui.cloud.common.service;

import com.google.common.collect.Lists;
import com.jiangkui.cloud.user.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import java.util.List;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * package:    com.jiangkui.cloud.service
 * className:  DemoService
 * date:       2017/09/28 04:15
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@Service
public class DemoService {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 同步执行
	 * @param id
	 * @return
	 */
//	@HystrixCommand(fallbackMethod = "defaultUser", commandKey = "findUserById", groupKey = "userService", threadPoolKey = "userServiceThreadPool")
	@HystrixCollapser(batchMethod = "findByIdsAsync", collapserProperties = {
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
	})
	public User findUserById(Long id) {
		// http://服务提供者的serviceId/url
		return this.restTemplate.getForObject("http://user-service/user/" + id, User.class);
	}

	/**
	 * 更新
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "defaultCallBack", commandKey = "updateUserById", groupKey = "userService", threadPoolKey = "userServiceThreadPool")
	public boolean updateUserById(Long id) {
		return true;
	}

	/**
	 * 异步执行
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "defaultUser")
	public Future<User> findByIdAsync(Long id) {

		return new AsyncResult<User>(){
			@Override
			public User invoke() {
				// http://服务提供者的serviceId/url
				return restTemplate.getForObject("http://user-service/user/" + id, User.class);
			}
		};
	}

	@SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "defaultUserList")
	public Future<List<User>> findByIdsAsync(List<Long> ids) {

		return new AsyncResult<List<User>>(){
			@Override
			public List<User> invoke() {
				// http://服务提供者的serviceId/url
				return restTemplate.getForObject("http://user-service/user/" + ids, List.class);
			}
		};
	}

//	/**
//	 * 自定义命令实现
//	 * @param id
//	 * @return
//	 * @throws ExecutionException
//	 * @throws InterruptedException
//	 */
//	public User commandFind(Long id) throws ExecutionException, InterruptedException {
//		UserCommand userCommand = new UserCommand(restTemplate, id);
//		//同步
////		return userCommand.execute();
//		//异步
//		Future<User> userFuture = userCommand.queue();
//		return userFuture.get();
//	}

	/**
	 * 回调
	 * @param id    调用对应方法传入的参数
	 * @param e     远程调用异常处理
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "defaultCallBack")
	public User defaultUser(Long id, Throwable e) {
		System.out.println("error" + id);
		e.printStackTrace();
		return new User();
	}

	public List<User> defaultUserList() {
		return Lists.newArrayList();
	}
	/**
	 * 统一默认服务降级逻辑
	 * @param e
	 * @return
	 */
	public String defaultCallBack(Long id,Throwable e) {
		return e.getMessage();
	}
}