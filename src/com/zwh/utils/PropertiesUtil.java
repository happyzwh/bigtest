package com.zwh.utils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class PropertiesUtil {
    // 根据key读取value
    public static String readValue(String filePath, String key) {
        InputStream in = null;
        String value = null;
        try {
            Properties props = new Properties();
            if (StringUtils.isBlank(filePath))
                filePath = "/constants.properties";
            in = (new PropertiesUtil()).getClass()
                    .getResourceAsStream(filePath);
            props.load(in);
            if(props.containsKey(key)){
                value = props.getProperty(key);
            }
        } catch (Exception e) {

        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (Exception e) {

            }
        }
        return value;
    }

    // 读取properties的全部信息
    @SuppressWarnings("unchecked")
    public static void readProperties(String filePath) {
        InputStream in = null;
        try {
            Properties props = new Properties();
            if (StringUtils.isBlank(filePath))
                filePath = "/constants.properties";
            in = (new PropertiesUtil()).getClass()
                    .getResourceAsStream(filePath);
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String Property = props.getProperty(key);
                System.out.println(key + Property);
            }
        } catch (Exception e) {
            // e.printStackTrace();
            // System.err.println("ConfigInfoError" + e.toString());
        }
    }

    // 写入properties信息
    public static void writeProperties(String filePath, String parameterName,
            String parameterValue) {
        OutputStream fos = null;
        try {
            Properties prop = new Properties();
            if (StringUtils.isBlank(filePath))
                filePath = "constants.properties";
            fos = new FileOutputStream((new PropertiesUtil()).getClass().getResource("/").getPath()+filePath);
            prop.setProperty(parameterName, parameterValue);
            prop.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
            // e.printStackTrace();
        }finally {
            try {
                if(fos != null) {
                    fos.close();
                    fos = null;
                }
            } catch (Exception e) {

            }
        }
    }

    public static void main(String args[]) {
        String filePath = "constants.properties";
        PropertiesUtil.writeProperties(filePath, "qq1112", "ww222111nnnmmmm");
        System.out.println(PropertiesUtil.readValue("/"+filePath, "qq1112"));
    }
}