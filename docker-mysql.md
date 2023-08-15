### 映射文件配置启动
````
1.拉取镜像
docker pull mysql:8.0.30
2.创建挂在磁盘
mkdir -p /usr/etc/mysql/data
mkdir -p /usr/etc/mysql/logs
mkdir -p /usr/etc/mysql/conf

3.配置文件
 
//根目录选择自身实际最大磁盘路径 一般为/home 或者定义/data 
vi /usr/etc/mysql/conf/my.cnf
 
//insert 插入以下内容
 
[mysqld]
 
#服务端口号 默认3306
port=3306
user=mysql
#mysql数据文件所在位置
datadir=/var/lib/mysql
 
#pid
pid-file=/var/run/mysqld/mysqld.pid
socket=/var/run/mysqld/mysqld.sock
default-time-zone = '+8:00'
 
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
# 允许访问的IP网段
bind-address=0.0.0.0
 
#只能用IP地址检查客户端的登录，不用主机名
skip_name_resolve=1
 
#事务隔离级别，默认为可重复读，mysql默认可重复读级别（此级别下可能参数很多间隙锁，影响性能）
transaction_isolation=READ-COMMITTED
 
 
#最大连接数
max_connections=400
 
#最大错误连接数
max_connect_errors=1000
 
#TIMESTAMP如果没有显示声明NOT NULL，允许NULL值
explicit_defaults_for_timestamp=true
 
#SQL数据包发送的大小，如果有BLOB对象建议修改成1G
max_allowed_packet=1G

4. 启动
# -p 端口映射 33060:3306 指外部访问端口改成33060规避一些默认端口被禁情况
 
docker run --privileged=true -p 33060:3306 -m 1g --name mysql -v /usr/etc/mysql/conf:/etc/mysql/conf.d -v /usr/etc/mysql/logs:/var/log/mysql -v /usr/etc/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=keystoiot -d mysql:8.0.30

5. 检查
#检查镜像是否正常运行
docker ps
 
#进入容器
docker exec -it mysql bash
 
#用默认密码登陆账号
mysql -uroot -p

6. 创建账号
#创建账号并授权
CREATE USER 'test'@'%' IDENTIFIED BY 'test';
CREATE DATABASE test_database CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
GRANT ALL PRIVILEGES ON test_database .* TO 'test'@'%';
FLUSH PRIVILEGES;