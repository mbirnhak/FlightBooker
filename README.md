# Flight Booking Application

## Project application nature and purpose
  This project consists of an application that searches for available flights based on "leaving from", "going to" and "departure date", displays         available seats and allows the user to purchase a ticket, which will then be send to his/her email.

## Team members
  Matthew Birnhak and Benny Simoes

## Modules
  We will have a module (microservice) for the following areas:
  
  - Front-end
      - Deals with user interaction
 

  - Aggregator
      - Connects to all other components of appciation and recieves user requests from the UI


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

## Languages and Frameworks
  - Java
  - Mongo
  - HTML
  - CSS
  - Javascript
  - Python
  - Shell Script
  - SpringBoot
  - Flask


## Diagram:
<img width="1100" alt="Diagram" src="https://github.com/mbirnhak/Kata-Project/assets/123829531/2f85d5de-e2ec-4640-bc33-9b8f16e42112">
  
## To Download the App
`git clone https://github.com/mbirnhak/Kata-Project.git`

## To Build the App (May take a few minutes depending on your internet connection)
`cd Kata-Project && cd kubernetes && chmod +x build-app.sh && bash build-app.sh build DOCKER_HUB_USERNAME`

## To Deploy the App on Kubernetes
`chmod +x apply-yaml.sh && bash apply-yaml.sh DOCKER_HUB_USERNAME`

## To Access the UI
Go to http://localhost:30101/

## To Deploy the App on GKE
1. Once downloaded locally and built (see section above "To Build the App") complete the following
2. Create a cluster on GKE
3. Upload the kubernetes folder to your cluster
4. Run the following command:
`cd kubernetes && chmod +x apply-yaml.sh && bash apply-yaml.sh DOCKER_HUB_USERNAME`
5. Open a hole in the firewall:
`gcloud compute firewall-rules create flight-app-node-port --allow tcp:30101`
6. Get an External-IP of one of the nodes in your cluster:
`kubectl get nodes -o wide`
7. Finally, access the UI at http://{External-IP}:30101/
