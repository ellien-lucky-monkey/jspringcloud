package com.jiangkui.cloud.user.service;

import com.jiangkui.cloud.core.utils.ValidateUtil;
import org.springframework.stereotype.Service;

/**
 * package:    com.jiangkui.cloud.service
 * className:  HelloService
 * date:       2017/09/28 04:05
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@Service
public class HelloService {

	public String hello() {
		ValidateUtil.validEmail("1145315469@qq.com");
		return "hello";
	}
}