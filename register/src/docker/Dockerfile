# 基于哪个镜像
FROM java:8

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器，也可以直接写成ADD register-1.0-SNAPSHOT.jar /app.jar
ADD  /target/register-1.0-SNAPSHOT.jar /opt/docker/app.jar
RUN bash -c 'touch /app.jar'

# 开放8761端口
EXPOSE 1111

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Dspring.profiles.active=peer1","-jar","/opt/docker/app.jar"]
# 构建镜像 docker build -f src/docker/Dockerfile -t register/peer1 .  其中context未为register目录
# 启动 docker run --name register1  -p 1111:1111 -t register/peer1 bash