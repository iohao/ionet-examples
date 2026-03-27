# ionet-multi-process

An example of **multi-process deployment** on the same machine based on the [ionet](https://github.com/iohao/ionet) framework. It demonstrates how to split the external service and multiple logic servers into independent processes and communicate through Aeron IPC.

## Project Structure

```text
ionet-multi-process
├── common-data          # Shared data: command IDs, Aeron configuration, utility classes
├── logic-book-library   # Book library logic server (independent process)
├── logic-author         # Author logic server (independent process)
├── one-application      # Aggregation service (independent process, embedded Aeron MediaDriver, external service, registry center)
└── one-client           # Test client
```

## Architecture Overview

```text
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

- **one-application**: starts the embedded Aeron MediaDriver, CenterServer, and Netty WebSocket external service
- **logic-book-library / logic-author**: independent logic services that connect to the MediaDriver through Aeron IPC
- **Inter-process invocation**: `AuthorAction.listBook` invokes `BookLibraryAction.listBook` across processes through `FlowContext.call()`

## Command ID Description

| Module | cmd | subCmd | Description |
|------|-----|--------|------|
| logic-book-library | 1 | 1 | `listBook` gets the book list |
| logic-author | 2 | 1 | `hello` greeting |
| logic-author | 2 | 2 | `listBook` gets the book list across processes |

## Environment Requirements

- Java 25+
- Maven 3.6+

## Packaging

Run the following in the project root directory to package all modules at once:

```bash
mvn clean package
```

Packaged artifacts:

| Module | JAR Path |
|------|---------|
| one-application | `one-application/target/one-application.jar` |
| logic-book-library | `logic-book-library/target/logic-book-library.jar` |
| logic-author | `logic-author/target/logic-author.jar` |
| one-client | `one-client/target/one-client.jar` |

Each JAR is a fat JAR containing all dependencies and can be run directly.

## Startup

> **Note**: You must start the services in the following order. `one-application` must be started first to create the Aeron MediaDriver.

**Step 1: Start the aggregation service (`one-application`)**

```bash
java --add-opens java.base/jdk.internal.misc=ALL-UNNAMED \
     --enable-native-access=ALL-UNNAMED \
     -jar one-application/target/one-application.jar
```

**Step 2: Start the logic services (two processes, order does not matter)**

```bash
# Book library service
java --add-opens java.base/jdk.internal.misc=ALL-UNNAMED \
     --enable-native-access=ALL-UNNAMED \
     -jar logic-book-library/target/logic-book-library.jar

# Author service
java --add-opens java.base/jdk.internal.misc=ALL-UNNAMED \
     --enable-native-access=ALL-UNNAMED \
     -jar logic-author/target/logic-author.jar
```

**Step 3: Start the client (`one-client`)**

```bash
java -jar one-client/target/one-client.jar
```

The client connects to the external port of `one-application` through WebSocket and automatically sends `AuthorCmd` requests for testing.

## Reference Documentation

- ionet official documentation: https://iohao.github.io/ionet
