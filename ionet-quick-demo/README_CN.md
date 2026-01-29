## 介绍

**语言**: [English](README.md) | 中文

**快速从零编写服务器完整示例-文档**

https://iohao.github.io/game/docs/quick_zero_demo

**运行步骤**

1. DemoApplication.java 服务器启动类
2. DemoClient.java 模拟客户端启动类



## 打 jar 包并运行

通过终端进入项目后，执行下面的命令，会在 target 目录会生成 `ionet-quick-demo.jar`。

```shell
mvn clean package
```



**启动 jar**

```shell
java \
--add-opens java.base/jdk.internal.misc=ALL-UNNAMED \
--enable-native-access=ALL-UNNAMED \
-jar target/ionet-quick-demo.jar
```



## docker 部署

准备工作，确保机器上有 docker 相关环境。以下是在终端执行的，首次使用 docker 部署、运行需要的时间会长一些，因为会下载相关的镜像。



**1、 打 jar 包，在示例目录的根目录执行如下命令**

```shell
mvn package
```



**2、在示例目录的根目录执行如下命令** 

不要遗漏命令中的点 “.”;

```shell
docker build -t ionet-quick-demo .
```



**3、查看当前镜像**

```shell
docker images ionet-quick-demo
```

> 当前步骤不是必须的，执行完这条命令后可以看见镜像是否存在。



**4、启动刚打包好的镜像**

```sh
docker run --name ionet-quick-demo \
  -p 10100:10100 \
  --shm-size=512m \
  ionet-quick-demo
```

## JVM Options
https://iohao.github.io/ionet/docs/faq

```shell
--add-opens java.base/jdk.internal.misc=ALL-UNNAMED
--enable-native-access=ALL-UNNAMED
```