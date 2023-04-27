FROM openjdk:17

# Set the working directory
WORKDIR /payment

# Copy the jar file
COPY build/libs/payment-0.0.1-SNAPSHOT.jar Payment.jar

# Expose the port
EXPOSE 8084

# Set the entry point
ENTRYPOINT ["java", "-jar", "Payment.jar"]
