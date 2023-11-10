### nexus3
````
1. 拉取镜像
docker pull sonatype/nexus3
2. 创建数据文件
mkdir -p /data/nexus
3. 启动容器
docker run -d -e "INSTALL4J_ADD_VM_PARAMS=-Xms512m -Xmx512m -XX:MaxDirectMemorySize=512m -Djava.util.prefs.userRoot=/nexus-data/javaprefs" --name nexus -p 8081:8081 -d --restart always -u root --privileged=true -v /data/nexus:/nexus-data sonatype/nexus3