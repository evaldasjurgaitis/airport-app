FROM openjdk:16

VOLUME /tmp

WORKDIR /airport-batch-service

ADD /build/libs/airport-batch.jar airport-batch.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "airport-batch.jar"]