## mysql读写分离

### 参考网址
https://www.cnblogs.com/blacksmith4/p/13748414.html

### 说明
1. 本项目整合 SpringBoot+MybatisPlus配置读写分离，支持事务，默认执行链接写数据库
2. 本项目添加了aop的日志采集，其中对异常数据将不会存日志记录，可以通过全局异常处理进行入库操作

#### 学习知识点
1. docker搭建mysql的主从复制
2. aop日志的采集
3. 数据库的读写分离