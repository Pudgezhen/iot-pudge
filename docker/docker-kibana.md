### kibana
````
1. 拉取镜像
docker pull kibana:7.4.2

2. 启动容器
docker run --name kibana -e ELASTICSEARCH_USERNAME=elastic -e ELASTICSEARCH_PASSWORD=keystoiot -m 1024m --restart=always -e ELASTICSEARCH_HOSTS=http://1.94.58.206:9200 -p 5601:5601 -d kibana:7.4.2