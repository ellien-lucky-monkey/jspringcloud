package com.jiangkui.cloud.core.utils;

import java.io.IOException;
import java.util.Properties;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtil {

    public static String getProperty(String name) {
        String result = null;
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties("ye.properties");
            result = props.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
