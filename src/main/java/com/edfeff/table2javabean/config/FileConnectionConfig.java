package com.edfeff.table2javabean.config;

import com.edfeff.table2javabean.util.PropertiesUtil;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class FileConnectionConfig implements ConnectionConfig {

    PropertiesUtil propertiesUtil = null;

    public FileConnectionConfig(File file) {
        propertiesUtil = new PropertiesUtil(file);
    }

    public FileConnectionConfig(InputStream inputStream) {
        propertiesUtil = new PropertiesUtil(inputStream);
    }

    public FileConnectionConfig(URL url) {
        propertiesUtil = new PropertiesUtil(url);
    }

    public FileConnectionConfig(String fileName) {
        propertiesUtil = new PropertiesUtil(fileName);
    }

    @Override
    public String getUsername() {
        return propertiesUtil.get("db.username");
    }

    @Override
    public String getPassword() {
        return propertiesUtil.get("db.password");
    }

    @Override
    public String getUrl() {
        return propertiesUtil.get("db.url");
    }

    @Override
    public String getDriverClass() {
        return propertiesUtil.get("db.driver-class");
    }
}
