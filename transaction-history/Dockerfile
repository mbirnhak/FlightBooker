FROM openjdk:17

# Set the working directory
WORKDIR /transactionhistory

# Copy the jar file
COPY build/libs/transaction-history-0.0.1-SNAPSHOT.jar TransactionHistory.jar

# Expose the port
EXPOSE 8082

# Set the entry point
ENTRYPOINT ["java", "-jar", "TransactionHistory.jar"]
