### es单机
````
1. 拉取镜像
docker pull elasticsearch:7.4.2
2. 服务器配置
echo "vm.max_map_count=262144" >> /etc/sysctl.conf
/sbin/sysctl -p
3. 启动配置
mkdir -p /data/elasticsearch/{config,plugins,data}
4. 准备配置文件
vim /usr/local/elasticsearch/config/elasticsearch.yml
    # 集群名
    cluster.name: docker-cluster
    # 节点名
    node.name: node
    # 监听ip
    network.host: 0.0.0.0
    # 开启x-pack插件,用于添加账号密码
    xpack.security.enabled: true
5. 添加ik分词器
    https://github.com/medcl/elasticsearch-analysis-ik/releases/tag/v7.4.2
    解压到plugins 目录下unzip elasticsearch-analysis-ik-7.4.2.zip -d analysis-ik
6. 设置配置权限
    chown 1000:1000 /data/elasticsearch -R
7. 启动镜像
    docker run -d --name elasticsearch \
    -v /data/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
    -v /data/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
    -v /data/elasticsearch/data:/usr/share/elasticsearch/data \
    -v /etc/localtime:/etc/localtime \
    -e ES_JAVA_OPTS="-Xms512m -Xmx512m" \
    -e "discovery.type=single-node" \
    -p 9200:9200 -p 9300:9300 \
    --restart=always \
    elasticsearch:7.4.2
8. 设置密码
    docker exec -it elasticsearch bash
    elasticsearch-setup-passwords interactive