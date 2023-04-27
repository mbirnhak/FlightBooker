FROM openjdk:17

# Set the working directory
WORKDIR /aggregator

# Copy the jar file
COPY build/libs/aggregator-0.0.1-SNAPSHOT.jar Aggregator.jar

# Expose the port
EXPOSE 8085

# Set the entry point
ENTRYPOINT ["java", "-jar", "Aggregator.jar"]
