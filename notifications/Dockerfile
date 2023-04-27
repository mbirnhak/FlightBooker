FROM openjdk:17

# Set the working directory
WORKDIR /notifications

# Copy the jar file
COPY build/libs/notifications-0.0.1-SNAPSHOT.jar Notifications.jar

# Expose the port
EXPOSE 8087

# Set the entry point
ENTRYPOINT ["java", "-jar", "Notifications.jar"]
