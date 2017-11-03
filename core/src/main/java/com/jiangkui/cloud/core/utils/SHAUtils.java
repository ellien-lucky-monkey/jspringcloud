package com.jiangkui.cloud.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * package:com.qincai.ye.util
 * author:jiangkui
 * date: 2017/08/29 13:03
 */
public class SHAUtils {

	public static String SHA256(final String decript) {
		return SHA(decript, "SHA-256");
	}

	public static String SHA512(final String decript) {
		return SHA(decript, "SHA-512");
	}

	public static String SHA(final String decript, final String type) {
		String result = null;
		if (decript != null && decript.length() > 0) {
			try {
				MessageDigest digest = MessageDigest.getInstance(type);
				//传入要加密的字符串得到byte类型的结果
				digest.update(decript.getBytes());
				byte byteBuffer[] = digest.digest();
				//字节数组转换为十六进制数
				StringBuffer strHexString = new StringBuffer();
				for (int i = 0; i < byteBuffer.length; i++) {
					String hex = Integer.toHexString(0xff & byteBuffer[i]);
					if (hex.length() == 1) {
						strHexString.append('0');
					}
					strHexString.append(hex);
				}
				// 得到返回結果
				result = strHexString.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
