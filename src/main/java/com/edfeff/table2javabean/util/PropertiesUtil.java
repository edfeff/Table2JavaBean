package com.edfeff.table2javabean.util;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * 属性文件解析类
 *
 * @author wpp
 */
public class PropertiesUtil {
    private Properties properties;

    public PropertiesUtil(Properties properties) {
        this.properties = properties;
    }

    public PropertiesUtil() {
    }

    public PropertiesUtil(InputStream inputStream) {
        load(inputStream);
    }

    public PropertiesUtil(File file) {
        load(file);
    }

    public PropertiesUtil(String name) {
        load(name);
    }

    public PropertiesUtil(URL url) {
        InputStream inputStream = null;
        try {
            inputStream = url.openStream();
            load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean load(InputStream inputStream) {
        this.properties = new Properties();
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private boolean load(File file) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        return load(inputStream);
    }

    private boolean load(String fileName) {
        return load(new File(fileName));
    }

    public String get(String name) {
        return this.properties.getProperty(name);
    }
}
