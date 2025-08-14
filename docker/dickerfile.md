#### dockerfile
指令
- FROM: 指定基础镜像 {镜像名}:{镜像版本}
    示例: FROM openjdk:8
- RUN: 执行shell命令
    示例: RUN echo "hello world"
- COPY: 拷贝宿主机文件到镜像指定路径 {宿主机文件路径}:{镜像文件路径}
    示例: COPY /usr/local/tlias-data/static /usr/local/tlias-data/static
- ENV: 设置环境变量 key=value 可在后续指令中使用
    示例: ENV PORT 8080
- WORKDIR: 设置工作目录(切换到指定目录, 相当于 cd)
    示例: WORKDIR /app

#### 构建镜像
- docker build -t {镜像名}:{镜像版本} {Dockerfile文件路径 如果是当前目录就写.}
    示例: docker build -t onedimension/tlias-web-management:1.0-SNAPSHOT .