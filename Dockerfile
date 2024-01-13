# Use a base image that includes xargs
FROM ubuntu:latest

# Add the apt repositories
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

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
