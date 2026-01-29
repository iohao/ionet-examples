#FROM openjdk:25-jdk-oracle
FROM eclipse-temurin:25-jre-alpine
MAINTAINER 渔民小镇 <262610965@qq.com>
COPY ./target/ionet-quick-demo.jar ionet-quick-demo.jar
EXPOSE 10100
ENTRYPOINT ["java", \
            "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED", \
            "--enable-native-access=ALL-UNNAMED", \
            "-jar", "ionet-quick-demo.jar"]