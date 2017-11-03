package com.jiangkui.cloud.core.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import org.springframework.util.ReflectionUtils;

/**
 * package:com.qincai.ye.util
 * author:jiangkui
 * date: 2017/08/24 14:08
 */
public class ReflectUtil {

	public static Field findField(Object obj, String name) {
		Field field = ReflectionUtils.findField(obj.getClass(), name);
		if(!field.isAccessible()){
			field.setAccessible(true);
		}
		return field;
	}

	public static Field findField(List<?> objs, String name) {
		Field field = null;
		if(!isEmpty(objs)){
			field = ReflectionUtils.findField(objs.get(0).getClass(), name);
		}
		return field;
	}

	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}
}
