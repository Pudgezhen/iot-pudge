### gitlab
````
1. 拉取镜像
docker pull twang2218/gitlab-ce-zh

2. 启动容器
docker run -d -p 8443:443 -p 8090:80 -p 8022:22 --restart always --name gitlab -v /data/gitlab/etc:/etc/gitlab -v /data/gitlab/log:/var/log/gitlab -v /data/gitlab/data:/var/opt/gitlab --privileged=true twang2218/gitlab-ce-zh

3. 进入容器
docker exec -it gitlab bash

4. 修改gitlab.rb文件
cd /etc/gitlab
vim gitlab.rb
    
    external_url 'http://1.94.106.131'
    gitlab_rails['gitlab_sh_host'] = "1.94.106.131"
    gitlab_rails['gitlab_shell_ssh_port'] = 8022

5. 配置gitlab.yml文件
cd /opt/gitlab/embedded/service/gitlab-rails/config
vim gitlab.yml
    主要修改hosts 和 port

6. 重启服务
    gitlab-ctl restart
