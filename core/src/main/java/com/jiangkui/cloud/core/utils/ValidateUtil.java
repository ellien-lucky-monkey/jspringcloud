package com.jiangkui.cloud.core.utils;

import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 * package:    com.jiangkui.cloud.core.utils
 * className:  ValidateUtil
 * date:       2017/09/29 10:50
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
public class ValidateUtil {
	/**
	 * 手机号正则匹配
	 */
	private static final Pattern REGEX_MOBILE = Pattern.compile("^0?1(?:3[0-9]|4[579]|5[0-35-9]|7[01235678]|8[012345-9])\\d{8}$");
	/**
	 * 邮箱正则匹配
	 */
	private static final Pattern REGEX_EMAIL = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$");

	/**
	 * 验证手机号 1，非空 2，长度11位 3，正则匹配
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean validMobile(String mobile) {
		return !StringUtils.isEmpty(mobile)
				&& mobile.length() == 11
				&& REGEX_MOBILE.matcher(mobile).matches();
	}

	/**
	 * 验证邮箱
	 * @param email
	 * @return
	 */
	public static boolean validEmail(String email) {
		return !StringUtils.isEmpty(email) && REGEX_EMAIL.matcher(email).matches();
	}
}