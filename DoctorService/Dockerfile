FROM openjdk:17-jdk-alpine
COPY target/DoctorService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]