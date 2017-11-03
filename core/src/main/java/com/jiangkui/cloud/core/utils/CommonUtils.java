package com.jiangkui.cloud.core.utils;

import java.util.UUID;

/**
 * package:com.qincai.ye.util
 * author:jiangkui
 * date: 2017/08/28 11:22
 */
public class CommonUtils {

	public static void sendEmail(String email, String title, String content){

	}

	public static String generateUUID(boolean includeDelimiter) {
		String uuid = UUID.randomUUID().toString();
		if (!includeDelimiter) {
			uuid = uuid.replaceAll("-", "");
		}
		return uuid;
	}
}
