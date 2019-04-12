package com.edfeff.table2javabean.po;

import com.edfeff.table2javabean.util.TableNamConverter;
import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据表类
 *
 * @author wpp
 */
public class Table {

    String name;
    List<Row> list;
    Row keyRow;

    String basePackage = "";

    public Table(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    public void addRow(Row row) {
        this.getList().add(row);
        if ("PRI".equals(row.getKey())) {
            keyRow = row;
        }
    }

    private String getTableDefinition() {
        return "@Entity\n@Table(name = \"" + name + "\")\n";
    }

    private String getPackageDefinition() {
        return "".equals(basePackage) ? "" : "package " + basePackage + ";\n";
    }

    private String getClassDefinitionBegin() {
        return "public class " + StringUtils.capitalize(TableNamConverter.converterName(name)) + "{\n";
    }

    private String getClassDefinitionEnd() {
        return "}\n";
    }

    /**
     * 返回一个JavaBean的字符串
     *
     * @return
     */
    public String toBean() {
        StringBuilder s = new StringBuilder();
        s.append(getTableDefinition());
        s.append(getPackageDefinition());
        s.append(getClassDefinitionBegin());
        s.append(keyRow.getIdDefinition());
        list.forEach(e -> {
            s.append(e.getColumnDefinition());
            s.append(e.getFieldDefinition());
            s.append("\n");
        });
        s.append(getClassDefinitionEnd());
        return s.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Row> getList() {
        return list;
    }

    public void setList(List<Row> list) {
        this.list = list;
    }

    public Row getKeyRow() {
        return keyRow;
    }

    public void setKeyRow(Row keyRow) {
        this.keyRow = keyRow;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
