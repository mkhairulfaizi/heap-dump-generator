# Use an OpenJDK 11 image as the base
FROM openjdk:11-jdk-slim

# Set application directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
