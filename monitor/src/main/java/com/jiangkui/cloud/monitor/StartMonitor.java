package com.jiangkui.cloud.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * package:    com.jiangkui.cloud.monitor
 * className:  StartMonitor
 * date:       2017/11/09 17:09
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */

@SpringBootApplication
@EnableAdminServer
public class StartMonitor {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StartMonitor.class).run(args);
	}
}