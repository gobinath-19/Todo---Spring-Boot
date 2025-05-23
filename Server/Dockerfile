# Use official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/*.jar app.jar

# Expose port (Render uses environment variable PORT)
EXPOSE 2000

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
