### jenkins
````
1. 拉取镜像
docker pull jenkins/jenkins 

2. 创建配置文件夹
mkdir -p /data/jenkins
chmod 777 /data/jenkins

3. 配置maven
    cd /usr/local
    wget https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
    tar zxvf apache-maven-3.8.6-bin.tar.gz
    mv apache-maven-3.8.6 maven
    cd /usr/local/maven/conf
    vi settings.xml
 
<mirrors>
<mirror>
<id>nexus-aliyun</id>
<mirrorOf>central</mirrorOf>
<name>Nexus aliyun</name>
<url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
</mirrors>

4. 安装jdk


2. 启动容器
docker run -d -p 8080:8080 -p 50000:50000 --restart=always -v /data/jenkins:/var/jenkins_home -v /etc/localtime:/etc/localtime --name jenkins jenkins/jenkins