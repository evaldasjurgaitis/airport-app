FROM openjdk:16

WORKDIR /airport-backend-service

ADD /target/airport-backend.jar airport-backend.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "airport-backend.jar"]