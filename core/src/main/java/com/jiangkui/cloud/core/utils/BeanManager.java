package com.jiangkui.cloud.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * package:    com.jiangkui.cloud.core.utils
 * className:  BeanManager
 * date:       2017/10/11 10:42
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
public class BeanManager{

	private static ApplicationContext applicationContext = null;

	public void destroy() throws Exception {
		cleanApplicationContext();
	}


	public  static void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * 如果有多个Bean符合Class, 取出第一个.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		checkApplicationContext();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 检查applicationContext不为空
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicationContext未注入");
		}
	}

	/**
	 * 清空applicationContext
	 */
	private static void cleanApplicationContext() {
		applicationContext = null;
	}

	/**
	 * 获取 applicationContext
	 *
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}