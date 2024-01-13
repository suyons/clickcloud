# Base image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy your project files
COPY . .

# Build the application
RUN ./gradlew clean build

# Expose the port that your Spring Boot application will run on
EXPOSE 80

# Specify the default command to run when the container starts
CMD ["java", "-jar", "build/libs/clickcloud-0.0.1-SNAPSHOT.jar"]
