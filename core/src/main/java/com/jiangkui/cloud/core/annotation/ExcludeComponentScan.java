package com.jiangkui.cloud.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * package:    com.jiangkui.cloud.core.annotation
 * className:  ExcludeComponentScan
 * date:       2017/10/11 14:06
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcludeComponentScan {
}
