# Use an OpenJDK runtime
FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/code-review-backend.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
