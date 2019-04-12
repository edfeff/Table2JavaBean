package com.edfeff.table2javabean.helper;

import java.sql.ResultSet;

/**
 * Result处理器接口
 *
 * @author wpp
 */
public interface ResultSetHandler {
    Object resolve(ResultSet resultSet);
}
