# Step 1: Use the OpenJDK base image
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the jar file (make sure to build your Spring Boot application first)
COPY target/service_registry-0.0.1-SNAPSHOT.jar /app/service-registry.jar

# Step 4: Expose the port the app listens on (default Spring Boot port is 8080)
EXPOSE 8761

# Step 5: Run the Spring Boot application
CMD ["java", "-jar", "/app/service-registry.jar"]
