# Use OpenJDK base image for building the application
FROM openjdk:17-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory into the container's /app directory
COPY target/emirojobs-0.0.1-SNAPSHOT.jar /app/emirojobs.jar

# Expose port 8080 for the Spring Boot app to listen on
EXPOSE 8080

# Set the entry point to run the Spring Boot application using java -jar
ENTRYPOINT ["java", "-jar", "emirojobs.jar"]

