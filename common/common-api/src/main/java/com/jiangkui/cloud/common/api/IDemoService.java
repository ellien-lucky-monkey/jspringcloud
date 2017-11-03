package com.jiangkui.cloud.common.api;

import com.jiangkui.cloud.user.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * package:    com.jiangkui.cloud.common.api
 * className:  IDemoService
 * date:       2017/10/11 14:56
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@FeignClient(name = "user-service")
public interface IDemoService {
	/**
	 * 这里使用springMvc的注解映射具体的路径 也就是 user-service的UserController的findById方法的http请求路径
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	User findById(@PathVariable("id") Long id);
}
