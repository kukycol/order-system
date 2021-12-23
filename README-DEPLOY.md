### 部署过程

##### 用到的工具

- VMware Workstation Pro虚拟机
- FinalShell ssh连接工具
- navicat12 连接mysql数据库
- redis-manager redis可视化连接工具
- IntelliJ IDEA 2021.2.3 javaweb后端开发工具
- WebStorm 2021.2.3 vue前端开发工具

##### 运行项目文件前的准备

1. VMware Workstation Pro虚拟机中安装的centos7

   ```
   #安装进程查看工具
   yum install net-tools
   
   #安装wget下载工具
   yum install wget
   ```

2. centos7中安装java8

   ```
   #下载java8安装包
   
   #安装
   rpm -ivh xxx.rpm
   
   #javac、java进行验证是否安装并配置环境成功
   ```

3. centos7安装nginx

   ```
   #参考博客 地址：https://blog.csdn.net/qq_37345604/article/details/90034424
   
   #安装nginx前依赖准备
   yum -y install gcc
   yum install -y pcre pcre-devel
   yum install -y zlib zlib-devel
   yum install -y openssl openssl-devel
   
   #下载ng包
   wget http://nginx.org/download/nginx-1.9.9.tar.gz  
   
   #解压ng包
   tar -zxvf  nginx-1.9.9.tar.gz
   
   #切换到解压后的目录下后逐一进行下面三个命令
   ./configure
   make
   make install
   
   #启动
   #切换到安装目录/usr/local/nginx/sbin运行下面的命令
   ./nginx
   ```

4. centos7中安装docker

   ```
   #安装docker依赖
   yum install -y yum-utils device-mapper-persistent-data lvm2
   
   #设置 yum（阿里的yum）
   yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
   
   #yum 安装 docker
   yum install docker-ce-18.03.1.ce
   
   #验证
   docker --version
   
   #启动docker
   systemctl start docker
   #设置docker开机自启
   systemctl enable docker
   
   #修改下载源，提升pull镜像的速度
   #vi 编辑daemom.json
   vi /etc/docker/daemon.json
   
   #内容
   #如果文件为空则添加以下：
   {   "registry-mirrors" : ["https://docker.mirrors.ustc.edu.cn"]		}
   #如果文件不为空新增以下：
   "registry-mirrors" : ["https://docker.mirrors.ustc.edu.cn"]
   
   #重载 daemon.json 配置文件
   systemctl daemon-reload
   ```

5. docker安装mysql8.0.26

   ```
   #先创建mysql的配置文件用于挂载的mysql容器中
   vi /opt/docker_v/mysql/conf/my.cnf
   
   #my.cnf内容
   [mysqld]
   user=mysql
   character-set-server=utf8
   default_authentication_plugin=mysql_native_password
   secure_file_priv=/var/lib/mysql
   expire_logs_days=7
   sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
   max_connections=1000
   ngram_token_size=2
   [client]
   default-character-set=utf8
   
   [mysql]
   default-character-set=utf8
   
   #拉取mysql镜像
   docker pull mysql:8.0.26
   
   #查看是否拉取成功
   docker images
   
   #安装并运行mysql容器
   docker run \
   --name mysql8 \
   --restart=always \
   -it -p 3310:3306 \
   -v /opt/docker_v/mysql/conf/my.cnf:/etc/mysql/my.cnf \
   -e MYSQL_ROOT_PASSWORD=sat..123456 \
   -d mysql:8.0.26 \
   --lower_case_table_names=1 
   
   #解释
   -p 3310(centos7中的端口):3306(容器中的端口)
   --name 容器名称
   -v 挂载
   -e 容器环境配置
   --lower_case_table_names=1 数据库表名允许大小写
   mysql:8.0.26 镜像
   
   #关闭centos防火墙或者放行3310端口，使用navicat工具进行连接测试
   ```

6. docker安装redis

   ```
   #拉取redis镜像
   docker pull redis
   
   #运行并安装
   docker run -itd --name redis-test -p 6379:6379 redis
   
   #关闭centos防火墙或者放行6379端口，使用redis-manager工具进行连接测试
   ```

##### 运行项目

###### 部署后端

```
#用idea工具执行mvn clean package -DskipTests命令打包成jar文件
#上传至centos服务器，至于上传到哪个目录下，根据自己而定

#运行
nohup java -jar -Dspring.profiles.active=prod  /opt/project/admin/APlan-admin-1.0.0.jar > /opt/project/admin/admin.log &

#关闭centos防火墙或者放行8986端口
```

###### 部署前端

```
#使用webstorm执行npm run build:prod命令打包成dist文件夹
#上传至centos服务器，至于上传到哪个目录下，根据自己而定

#配置ng
#内容
    server {
    	listen       9527;
    	server_name  127.0.0.1;
    	root         /usr/local/nginx/html/9527/dist;
    	add_header X-Frame-Options sameorigin always;

  
    location / {
    }
        
	# 跨域配置
	location /prod-api {
		rewrite  ^/prod-api/(.*)$ /$1 break;
		proxy_pass   http://127.0.0.1:8986;
    	}
        
    }
    
#切换到安装目录/usr/local/nginx/sbin运行下面的命令重载ng配置
./nginx -s reload

#关闭centos防火墙或者放行9527端口
```

##### centos命令

```
#防火墙端口放行
firewall-cmd --zone=public --add-port=8003/tcp --permanent

#放行后重载防火墙配置文件
firewall-cmd --reload

#查看放行端口列表
firewall-cmd --list-ports

#查看防火墙状态
systemctl status firewalld

#查看进程及端口信息
netstat -tunlp
```

##### docker命令

```
#设置docker容器自启动
docker update --restart=always 容器id/容器名称

#取消docker容器自启动
docker update --restart=no 容器id/容器名称

#查看本地镜像库
docker images

#查看正在运行的容器
docker ps

#查看全部容器
docker ps -a

#查看容器运行日志
docker logs 容器id/容器名称

#查看实时容器日志
docker logs -f 容器id/容器名称
```

