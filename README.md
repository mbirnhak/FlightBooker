# Kata-Project

# Project application nature and purpose
  This project consists of an application that searches for available flights based on "leaving from", "going to" and "departure date", displays         available seats and allows the user to purchase a ticket, which will then be send to his/her email.

# Team members
  Matthew Birnhak and Bernardo Simoes

# Estimated modules
  We will have a module (microservice) for the following areas:
  
  - Front-end of the application 
        - Deals with the user interface and connects to all other components of appciation


  - Available planes
        - Will either connect to a database to simulate real flights, or it will connect through an API to real time flight info
        - Will get info on available planes based on the date and place of the input info


  - Available seats
        - Will either connect to a database to simulate real flights, or it will connect through an API to real time flight info
        - Will get info on available seats within the flight chosen


  - User service (connects to database)
        - Will deal with the login, sign up, and guest information


  - Transaction history (connects to database)
        - Stores the users previous flight history and allows the user to view this info


  - Payment (connects to database)
        - Deals with the users payment for the ticket


  - Notifications (connects to database)
        - Sends the user confirmation of payment, and their plane ticket

# Estimated languages and frameworks
  Languages: Java, Mongo, and SpringBoot
  
  Framework: Microservices


  
