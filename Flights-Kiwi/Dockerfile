FROM openjdk:17

# Set the working directory
WORKDIR /flights

# Copy the jar file
COPY build/libs/flights-0.0.1-SNAPSHOT.jar Flights.jar

# Expose the port
EXPOSE 8081

# Set the entry point
ENTRYPOINT ["java", "-jar", "Flights.jar"]
