# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/SpringBootWithMongoDB-0.0.1-SNAPSHOT.jar SpringBootWithMongoDB.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "SpringBootWithMongoDB.jar"]