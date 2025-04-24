FROM openjdk:17-jdk-alpine
COPY target/PatientService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]





##FROM ubuntu:latest
##LABEL authors="administrator"
##
##ENTRYPOINT ["top", "-b"]
#
## Use OpenJDK 17 base image
#FROM openjdk:17-jdk-slim
#
## Set environment variable for app path
#ENV APP_HOME=/app
#
## Set working directory
#WORKDIR $APP_HOME
#
## Copy Maven-built JAR into the image
#COPY target/patient-service-0.0.1-SNAPSHOT.jar app.jar
#
## Expose the port that your service runs on
#EXPOSE 8081
#
## Run the JAR file
#ENTRYPOINT ["java", "-jar", "app.jar"]
