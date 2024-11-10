FROM openjdk:21
LABEL authors="Neo"
ARG JAR_FILE=target/*.jar
COPY ./target/hardko-store-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]