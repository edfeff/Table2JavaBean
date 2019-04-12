package com.edfeff.table2javabean;

import com.edfeff.table2javabean.config.ConnectionConfig;
import com.edfeff.table2javabean.config.FileConnectionConfig;
import com.edfeff.table2javabean.helper.SqlExecutor;
import com.edfeff.table2javabean.po.Row;
import com.edfeff.table2javabean.po.Table;
import com.edfeff.table2javabean.util.PropertiesUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主流程类,负责连接数据库，将数据表全部转换成JavaBean
 *
 * @author wpp
 */
public class SqlToBean {
    //目前只支持mysql数据库
    String SHOW_TABLES_MYSQL = "SHOW TABLES;";
    String DESC_TABLE_MYSQL = "DESC __TABLE__ ;";

    //    连接配置
    ConnectionConfig connectionConfig = null;
    Connection connection = null;
    //存储数据表名
    List<String> tableNameList = null;
    //存储数据表
    Map<String, Table> tableMap = null;

    public SqlToBean(ConnectionConfig connectionConfig) throws Exception {
        this.connectionConfig = connectionConfig;
        tableMap = new HashMap<>();
        //准备connection
        prepareConnection();
        //准备所有数据表
        prepareTableNameList();
        //初始化所有数据表
        initTableList();
    }

    private void prepareConnection() throws Exception {
        String username = connectionConfig.getUsername();
        String password = connectionConfig.getPassword();
        String url = connectionConfig.getUrl();
        String driverClass = connectionConfig.getDriverClass();

        Class.forName(driverClass);
        connection = DriverManager.getConnection(url, username, password);
    }

    public void prepareTableNameList() {
        SqlExecutor.execute(connection, SHOW_TABLES_MYSQL, resultSet -> {
            try {
                tableNameList = initList(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private void initTableList() {
        tableNameList.forEach(item -> {
            String descSql = DESC_TABLE_MYSQL.replaceAll("__TABLE__", item);
            Table t = (Table) SqlExecutor.execute(connection, descSql, resultSet -> {
                Table table = new Table(item);
                try {
                    while (resultSet.next()) {
                        Row row = new Row();
                        row.setField(resultSet.getString("Field"));
                        row.setType(resultSet.getString("Type"));
                        row.setNull(resultSet.getString("Null"));
                        row.setKey(resultSet.getString("Key"));
                        row.setDefault(resultSet.getString("Default"));
                        row.setExtra(resultSet.getString("Extra"));
                        table.addRow(row);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return table;
            });
            tableMap.put(item, t);
        });
    }


    private List<String> initList(ResultSet resultSet) throws SQLException {
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public void showAllTable() {
        tableMap.keySet().forEach(key -> {
            Table table = tableMap.get(key);
            System.out.println(table.toBean());
        });
    }

    public Map<String, Table> getTableMap() {
        return tableMap;
    }

    public static void main(String[] args) throws Exception {
        ConnectionConfig config = new FileConnectionConfig("D:\\study\\java\\spring\\springboot\\table2javabean\\src\\main\\resources\\config.properties");
        SqlToBean sqlToBean = new SqlToBean(config);
        sqlToBean.showAllTable();
    }
}
