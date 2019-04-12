package com.edfeff.table2javabean.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据表名转换类
 * t_ T_ p_ f_ 被去除，然后转成驼峰
 *
 * @author wpp
 */
public class TableNamConverter {
    public static String converterName(String name) {
        if (name.startsWith("t_") || name.startsWith("T_") || name.startsWith("p_") || name.startsWith("f_")) {
            name = name.substring(2, name.length());
        }
        if (name.contains("_")) {
            StringBuffer stringBuffer = new StringBuffer();
            Pattern pattern = Pattern.compile("_[a-zA-Z]");
            Matcher matcher = pattern.matcher(name);
            while (matcher.find()) {
                String group = matcher.group().substring(1, 2).toUpperCase();
                matcher.appendReplacement(stringBuffer, group);
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        }
        return name;
    }
}
