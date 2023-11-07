### 映射文件配置启动
```
拉取镜像
docker pull nacos/nacos-server:v2.1.1
#创建nacos 配置目录
 
mkdir -p /data/nacos/conf
mkdir -p /data/nacos/logs
 
# 设置配置文件
vim /data/nacos/conf/application.properties
 
配置项参考
##---------
 
opyright 1999-2018 Alibaba Group Holding Ltd.
server.servlet.context-path=/nacos
server.port=8848
spring.datasource.platform=mysql
### Count of DB:
db.num=1
#连接mysql8.0数据库
### Connect URL of DB:
db.url.0=jdbc:mysql://xxxx/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC&useSSL=false
db.user.0=xxxx
db.password.0=xxx
db.pool.config.connectionTimeout=30000
db.pool.config.validationTimeout=10000
db.pool.config.maximumPoolSize=20
db.pool.config.minimumIdle=2
nacos.naming.empty-service.auto-clean=true
nacos.naming.empty-service.clean.initial-delay-ms=50000
nacos.naming.empty-service.clean.period-time-ms=30000
management.metrics.export.elastic.enabled=false
management.metrics.export.influx.enabled=false
server.tomcat.accesslog.enabled=true
server.tomcat.accesslog.pattern=%h %l %u %t "%r" %s %b %D %{User-Agent}i %{Request-Source}i
server.tomcat.basedir=/home/nacos/
nacos.security.ignore.urls=/,/error,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-ui/public/**,/v1/auth/**,/v1/console/health/**,/actuator/**,/v1/console/server/**
nacos.core.auth.system.type=nacos
nacos.core.auth.enabled=true
nacos.core.auth.default.token.expire.seconds=18000
nacos.istio.mcp.server.enabled=false
nacos.core.auth.caching.enabled=true
nacos.core.auth.enable.userAgentAuthWhite=true
#注意授权认证开启需要配置下面三个参数
nacos.core.auth.plugin.nacos.token.secret.key=
nacos.core.auth.server.identity.key=
nacos.core.auth.server.identity.value=
##----
配置映射启动配置
docker run -d --name nacos -p 8848:8848 -p 9848:9848 -p 9849:9849 --privileged=true --restart=always -e JVM_XMS=128m -e JVM_XMX=128m -e MODE=standalone -e PREFER_HOST_MODE=hostname -v /data/nacos/logs:/home/nacos/logs -v /data/nacos/conf/application.properties:/home/nacos/conf/application.properties -d nacos/nacos-server:v2.1.1