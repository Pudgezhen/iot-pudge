### dashboard
````
1. 拉取镜像
docker pull bladex/sentinel-dashboard:1.8.0
2. 启动容器
docker run --name sentinel  -d -p 8858:8858 -p 8719:8719 -d bladex/sentinel-dashboard:1.8.0 -e username=sentinel -e password=sentinel -e server=localhost:8858
3. 容器退出时总是重启容器
docker update --restart=always sentinel
