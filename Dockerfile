FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER TWINKG

# Add the application's jar to the container
COPY target/professions-service-0.0.1-SNAPSHOT.jar professions-service.jar

EXPOSE 9011

#execute the application
ENTRYPOINT ["java","-jar","/professions-service.jar"]