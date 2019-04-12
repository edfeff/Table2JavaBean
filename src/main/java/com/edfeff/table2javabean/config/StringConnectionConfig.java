package com.edfeff.table2javabean.config;

public class StringConnectionConfig implements ConnectionConfig {
    private String username;
    private String password;
    private String url;
    private String driverClass;

    public StringConnectionConfig(String username, String password, String url, String driverClass) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.driverClass = driverClass;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getDriverClass() {
        return driverClass;
    }
}
