package com.edfeff;

import com.edfeff.table2javabean.SqlToBean;
import com.edfeff.table2javabean.config.ConnectionConfig;
import com.edfeff.table2javabean.config.FileConnectionConfig;

/**
 * 测试类
 *
 * @author wpp
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ConnectionConfig config = new FileConnectionConfig("D:\\study\\java\\spring\\springboot\\table2javabean\\src\\main\\resources\\config.properties");
        SqlToBean sqlToBean = new SqlToBean(config);
        sqlToBean.showAllTable();
    }
}
