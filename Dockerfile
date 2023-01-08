FROM openjdk:17-slim-bullseye

LABEL org.opencontainers.image.authors="Georgios Marmaris"

VOLUME ["/app/log"]

ENTRYPOINT ["java", "-jar", "/app/profile.jar"]

COPY target/profile-*.jar /app/profile.jar