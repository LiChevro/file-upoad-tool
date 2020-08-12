package com.szfg.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:59 2020-08-11
 **/
public class SettingUtil {

    /**
     * 获取Properties对象
     *
     * @return
     */
    private static Properties getProperties() {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            InputStream in = classLoader.getResource("setting.properties").openStream();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    public static String getValues(String key) {
        Properties properties = getProperties();
        return (String) properties.get(key);
    }

    /**
     * 新增/修改数据
     *
     * @param map
     */
    public static void setValues(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        Properties properties = getProperties();
        for (Map.Entry<String, String> m : map.entrySet()) {
            String key = m.getKey();
            String value = m.getValue();
            properties.setProperty(key, value);
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = classLoader.getResource("setting.properties").getPath();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            properties.store(fos, "注释");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                System.out.println("setting.properties文件流关闭出现异常");
            }
        }
    }
}
