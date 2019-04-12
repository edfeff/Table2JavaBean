package com.edfeff.table2javabean.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Mysql类型与Java类型转换类
 *
 * @author wpp
 */
public class TypeConverter {
    private static final Map<String, String> jdbc2JavaMap;

    static {
        jdbc2JavaMap = new HashMap<>();
        jdbc2JavaMap.put("bigint", "Long");
        jdbc2JavaMap.put("tinyint", "Byte");
        jdbc2JavaMap.put("smallint", "Short");
        jdbc2JavaMap.put("mediumint", "Integer");
        jdbc2JavaMap.put("integer", "Integer");
        jdbc2JavaMap.put("int", "Integer");
        jdbc2JavaMap.put("float", "Float");
        jdbc2JavaMap.put("double", "Double");
        jdbc2JavaMap.put("decimal", "BigDecimal");
        jdbc2JavaMap.put("numeric", "BigDecimal");
        jdbc2JavaMap.put("char", "String");
        jdbc2JavaMap.put("varchar", "String");
        jdbc2JavaMap.put("tinytext", "String");
        jdbc2JavaMap.put("date", "Date");
        jdbc2JavaMap.put("time", "Date");
        jdbc2JavaMap.put("year", "Date");
        jdbc2JavaMap.put("datetime", "Date");
        jdbc2JavaMap.put("timestamp", "Date");
    }

    public static String jdbc2JavaType(String jdbc) {
        return jdbc2JavaMap.get(jdbc);
    }
}
