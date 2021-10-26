FROM openjdk:8-jdk-alpine
ARG JAR_FILE=web/target/*.jar
COPY ${JAR_FILE} dchat.jar
ENTRYPOINT ["java","-jar","/dchat.jar"]