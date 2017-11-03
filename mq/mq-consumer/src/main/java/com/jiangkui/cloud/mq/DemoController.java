package com.jiangkui.cloud.mq;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package:    com.jiangkui.cloud.mq
 * className:  DemoController
 * date:       2017/10/09 14:19
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@RefreshScope
@RestController
public class DemoController {
	@RequestMapping("demo")
	public String demo() {
		return "hello demo";
	}
}