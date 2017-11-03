package com.jiangkui.cloud.user.controller;

import com.google.common.collect.Lists;
import com.jiangkui.cloud.user.User;
import java.util.List;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * package:    com.jiangkui.cloud.controller
 * className:  UserController
 * date:       2017/09/28 04:13
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@RestController
public class UserController {

	@GetMapping("/user/{id}")
	public User findById (@PathVariable Long id){
		User user = new User();
		user.setId(id);
		user.setUsername("ellien");
		return user;
	}

	@GetMapping("/user/{ids}")
	public List<User> findById (@PathVariable List<Long> ids){
		User user = new User();
		user.setId(new Random().nextLong());
		user.setUsername("ellien");
		return Lists.newArrayList(user);
	}
}