FROM openjdk:16-jdk-alpine
ARG JAR_FILE=target/shop.jar
COPY ${JAR_FILE} shop.jar
ENTRYPOINT ["java","-Dserver.port=$PORT","-jar","/shop.jar"]
