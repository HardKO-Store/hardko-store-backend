FROM openjdk:21
LABEL authors="Neo"
ARG JAR_FILE=target/*.jar
COPY ./target/hardko-store-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]