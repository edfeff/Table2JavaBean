package com.edfeff.table2javabean.po;

import com.edfeff.table2javabean.util.TableNamConverter;
import com.edfeff.table2javabean.util.TypeConverter;

/**
 * 数据表行数据类
 *
 * @author wpp
 */
public class Row {
    private String Field;
    private String Type;
    private String Null;
    private String Key;
    private String Default;
    private String Extra;

    private final String COLUMN = "@Column";
    private final String COLON = "\"";

    public String getType() {
        int i = Type.indexOf('(');
        String type = Type;
        if (i != -1) {
            type = Type.substring(0, i);
        }
        return TypeConverter.jdbc2JavaType(type.toLowerCase());
    }

    public boolean isPrimaryKey() {
        return "PRI".equals(Field);
    }

    public String getIdDefinition() {
        return "\t@Id\n\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n";
    }

    public String getColumnDefinition() {
        StringBuilder s = new StringBuilder();
        //@Column(
        s.append("\t");
        s.append(COLUMN).append("(");

        //@Column(name="name"
        s.append("name= ").append(COLON).append(Field).append(COLON);

        String len = Type.substring(Type.indexOf("(") + 1, Type.indexOf(")"));

        //@Column(name="name",
        s.append(",");

        //@Column(name="name",length=x
        s.append("length=").append(len);

        String nullable = "NO".equals(Null) ? "false" : "";

        if ("false".equals(nullable)) {
            //@Column(name="name",length=x,
            s.append(",");
            //@Column(name="name",length=x,nullable=true
            s.append("nullable=").append(nullable);
        }

        if ("UNI".equals(Key)) {
            //@Column(name="name",length=x,nullable=true,unique=true
            s.append(",unique=true");
        }
        s.append(")\n");
        return s.toString();
    }

    public String getFieldDefinition() {
        StringBuilder s = new StringBuilder();
        s.append("\t");
        s.append("private ").append(getType()).append(" ").append(TableNamConverter.converterName(Field)).append(";\n");
        return s.toString();
    }

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNull() {
        return Null;
    }

    public void setNull(String aNull) {
        Null = aNull;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getDefault() {
        return Default;
    }

    public void setDefault(String aDefault) {
        Default = aDefault;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }

    public String getCOLUMN() {
        return COLUMN;
    }

    public String getCOLON() {
        return COLON;
    }
}