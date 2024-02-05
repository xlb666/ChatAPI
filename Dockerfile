# 基础镜像
FROM openjdk:19
# 作者
MAINTAINER GG
# 项目的端口，内部服务端口
EXPOSE 8090
# 切换到容器内部的 /workdir目录
WORKDIR /workdir
# 配置
#ENV PARAMS=""
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 添加应用
#ADD out.artifacts.ChatAPI_jar/ChatAPI.jar /chat-api:1.0
# 添加要运行的jar文件,将第一个jar复制到该目录中并重命名
COPY /ChatAPI-interfaces/target/chat-api-jar-with-dependencies.jar /workdir/chat-api.jar
# 执行镜像
#ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /chat-api.jar $PARAMS"]
ENTRYPOINT ["java","-jar","/workdir/chat-api.jar"]


## 基础镜像
#FROM openjdk:8-jre-slim
## 作者
#MAINTAINER GG
## 配置
#ENV PARAMS=""
## 时区
#ENV TZ=PRC
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
## 添加应用
#ADD /ChatAPI-interfaces/target/chat-api.jar /chat-api.jar
## 执行镜像
#ENTRYPOINT ["java","-jar","/chat-api.jar"]
