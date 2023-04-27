FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the jar file
COPY build/libs/userservice-0.0.1-SNAPSHOT.jar UserService.jar

# Expose the port
EXPOSE 8083

# Set the entry point
ENTRYPOINT ["java", "-jar", "UserService.jar"]
