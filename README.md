# 数据表转换成JavaBean的工具

### 目的
简化数据表转换JavaBean的重复工作。
可以直接连接数据库，将数据库中的所有表直接转成JavaBean，简化操作。

### 现有功能
 1. 可以连接mysql数据库，将数据表转成JavaBean格式的字符串。
 2. 支持单表数据格式导出JavaBean
 
### 后续计划功能
   1. 自动导入依赖包
   2. 直接生成文件到指定目录
   3. 支持导入Lombok注解
   4. 支持多表连接
### 使用示例
```java
ConnectionConfig config =
 new FileConnectionConfig("D:\\study\\java\\spring\\springboot\\table2javabean\\src\\main\\resources\\config.properties");
SqlToBean sqlToBean = new SqlToBean(config);
sqlToBean.showAllTable();
```
