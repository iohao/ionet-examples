## Introduction

**Language**: English | [中文](README_CN.md)

**Quickly Write a Complete Server Example from Scratch - Documentation**

https://iohao.github.io/game/docs/quick_zero_demo

**Steps to Run**

1. `DemoApplication.java`: The server startup class.
2. `DemoClient.java`: The simulated client startup class.

---

## Package and Run the JAR

After entering the project directory via the terminal, execute the following command. A file named `ionet-quick-demo.jar` will be generated in the `target` directory.

```shell
mvn clean package
```

**Start the JAR**

```shell
java \
--add-opens java.base/jdk.internal.misc=ALL-UNNAMED \
--enable-native-access=ALL-UNNAMED \
-jar target/ionet-quick-demo.jar
```

---

## Docker Deployment

Preparation: Ensure that you have the necessary Docker environment installed on your machine. The following commands are executed in the terminal. The first time you use Docker for deployment and running, it may take longer due to the downloading of related images.

**1. Package the JAR. Execute the following command in the root directory of the example.**

```shell
mvn package
```

**2. Execute the following command in the root directory of the example.** Do not omit the dot "." in the command:

```shell
docker build -t ionet-quick-demo .
```

**3. Check the Current Image**

```shell
docker images ionet-quick-demo
```

> This step is not mandatory. Executing this command allows you to see if the image exists.

**4. Start the Image that was Just Packaged**

```sh
docker run --name ionet-quick-demo \
  -p 10100:10100 \
  --shm-size=512m \
  ionet-quick-demo
```

---

## JVM Options
https://iohao.github.io/ionet/docs/faq

```shell
--add-opens java.base/jdk.internal.misc=ALL-UNNAMED
--enable-native-access=ALL-UNNAMED
```