FROM openjdk:17-alpine
MAINTAINER techsingou
ARG JAR_FILE=target/customer-service.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

