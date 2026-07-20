#!/usr/bin/env bash
#
# Run GenerateTest via the exec-maven-plugin task configured in pom.xml.
# cn: 通过 pom.xml 中配置的 exec-maven-plugin 任务运行 GenerateTest。
#
set -euo pipefail

# Switch to the script directory (project root) so mvn always uses this pom.xml.
# cn: 切换到脚本所在目录（项目根目录），保证 mvn 使用本项目的 pom.xml。
cd "$(dirname "$0")"

# compile: build the sources first (exec:exec needs the compiled classes in target/classes)
# exec:exec: launch a real `java` process to run GenerateTest (supports the no-arg static void main())
# cn: 先编译源码，再启动独立 java 进程运行 GenerateTest
mvn compile exec:exec
