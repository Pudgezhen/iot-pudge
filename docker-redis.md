### 映射文件配置启动
````
1.拉取镜像
docker pull redis
2.创建挂载路径
mkdir -p /usr/etc/redis/data
mkdir -p /usr/etc/redis/conf
3.创建配置文件
 
//根目录选择自身实际最大磁盘路径 一般为/home 或者定义/data 
vi /data/redis/conf/redis.conf
 
其他配置见官方文档
https://www.redis.net.cn/tutorial/3504.html
 
 
requirepass CsPudge666 连接密码

4.启动
docker run --name=redis --volume=/usr/etc/redis/conf/redis.conf:/usr/local/etc/redis/redis.conf --volume=/usr/etc/redis/data:/data --volume=/data --workdir=/data -p 6379:6379 --restart=no --detach=true redis redis-server /usr/local/etc/redis/redis.conf --appendonly yes