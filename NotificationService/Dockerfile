FROM openjdk:17-jdk-alpine
COPY target/NotificationService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app.jar"]