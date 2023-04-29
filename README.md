# Kata-Project

# Project application nature and purpose
  This project consists of an application that searches for available flights based on "leaving from", "going to" and "departure date", displays         available seats and allows the user to purchase a ticket, which will then be send to his/her email.

# Team members
  Matthew Birnhak and Bernardo Simoes

# Modules
  We will have a module (microservice) for the following areas:
  
  - Aggregator
        - Connects to all other components of appciation and deals with user requests


  - Available planes
        - Connects to Tequila Flights API
        - Returns info on available flights based on the date, departure airport, and destination airport


  - Available seats
        - Gets info on available seats within the flight chosen
        - Stores info in a database


  - User service
        - Deals with user information
        - Stores info in database


  - Transaction
        - Stores the users previous flight purchases in a database
        - Allows the user to view this info
        - Allows the user to purchase new flights


  - Payment
        - Simulates dealing with the users credit card information


  - Notifications
        - Sends the user confirmation of payment and their plane ticket via email

# Languages and Frameworks
  Languages: Java, Mongo, and Shell Script
  
  Framework: SpringBoot


# Diagram:
<img width="919" alt="Screenshot 2023-04-29 at 3 04 54 PM" src="https://user-images.githubusercontent.com/123829531/235320232-fad47d85-62d2-4ba0-9439-4e17200d48e1.png">

  
