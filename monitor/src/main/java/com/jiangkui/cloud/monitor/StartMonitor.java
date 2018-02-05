package com.jiangkui.cloud.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * package:    com.jiangkui.cloud.monitor
 * className:  StartMonitor
 * date:       2017/11/09 17:09
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */

@SpringBootApplication
//@EnableDiscoveryClient
@EnableAdminServer
public class StartMonitor {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StartMonitor.class).run(args);
	}
}