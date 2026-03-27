# ionet-multi-process

基于 [ionet](https://github.com/iohao/ionet) 框架的同机**多进程部署**示例。演示如何将对外服、多个逻辑服务器拆分为独立进程，通过 Aeron IPC 进行通信。

## 项目结构

```
ionet-multi-process
├── common-data          # 共享数据：命令号、Aeron 配置、工具类
├── logic-book-library   # 图书馆逻辑服（独立进程）
├── logic-author         # 作者逻辑服（独立进程）
├── one-application      # 聚合服务（独立进程，内嵌 Aeron MediaDriver，对外服，注册中心）
└── one-client           # 测试客户端
```

## 架构说明

```
one-client (WebSocket)
    │
    ▼
one-application  ←── Aeron IPC ──► logic-book-library
  (CenterServer                   (BookLibraryLogicServer)
   ExternalServer                  cmd=1: listBook
   MediaDriver)
        │
        └─── Aeron IPC ──────────► logic-author
                                   (AuthorLogicServer)
                                    cmd=2: hello, listBook
```

- **one-application**：启动内嵌 Aeron MediaDriver、CenterServer 和 Netty WebSocket 对外服务
- **logic-book-library / logic-author**：独立逻辑服务，通过 Aeron IPC 连接到 MediaDriver
- **进程间调用**：`AuthorAction.listBook` 通过 `FlowContext.call()` 跨进程调用 `BookLibraryAction.listBook`

## 命令号说明

| 模块 | cmd | subCmd | 描述 |
|------|-----|--------|------|
| logic-book-library | 1 | 1 | `listBook` 获取图书列表 |
| logic-author | 2 | 1 | `hello` 问候 |
| logic-author | 2 | 2 | `listBook` 跨进程获取图书列表 |

## 环境要求

- Java 25+
- Maven 3.6+

## 打包

在项目根目录执行，一次性打包所有模块：

```bash
mvn clean package
```

打包产物：

| 模块 | JAR 路径 |
|------|---------|
| one-application | `one-application/target/one-application.jar` |
| logic-book-library | `logic-book-library/target/logic-book-library.jar` |
| logic-author | `logic-author/target/logic-author.jar` |
| one-client | `one-client/target/one-client.jar` |

每个 JAR 均为包含全部依赖的 fat JAR，可直接运行。

## 启动

> **注意**：必须按以下顺序启动，`one-application` 需要先启动以创建 Aeron MediaDriver。

**第一步：启动聚合服务（one-application）**

```bash
java --add-opens java.base/jdk.internal.misc=ALL-UNNAMED \
     --enable-native-access=ALL-UNNAMED \
     -jar one-application/target/one-application.jar
```

**第二步：启动逻辑服务（两个进程，顺序不限）**

```bash
# 图书馆服务
java --add-opens java.base/jdk.internal.misc=ALL-UNNAMED \
     --enable-native-access=ALL-UNNAMED \
     -jar logic-book-library/target/logic-book-library.jar

# 作者服务
java --add-opens java.base/jdk.internal.misc=ALL-UNNAMED \
     --enable-native-access=ALL-UNNAMED \
     -jar logic-author/target/logic-author.jar
```

**第三步：启动客户端（one-client）**

```bash
java -jar one-client/target/one-client.jar
```

客户端通过 WebSocket 连接到 `one-application` 的外部端口，自动发送 `AuthorCmd` 请求进行测试。

## 参考文档

- ionet 官方文档：https://iohao.github.io/ionet
