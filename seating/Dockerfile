FROM openjdk:17

# Set the working directory
WORKDIR /seating

# Copy the jar file
COPY build/libs/seating-0.0.1-SNAPSHOT.jar Seating.jar

# Expose the port
EXPOSE 8086

# Set the entry point
ENTRYPOINT ["java", "-jar", "Seating.jar"]
