### 直接启动
````
1. 拉去镜像
docker pull emqx/emqx-enterprise:5.1.1
2. 启动容器
docker run -d --name emqx-enterprise -p 1883:1883 -p 8083:8083 -p 8084:8084 -p 8883:8883 -p 18083:18083 emqx/emqx-enterprise:5.1.1
3.dashboard
http://192.168.186.130:18083/
admin   keysToIOT!
默认-admin        public