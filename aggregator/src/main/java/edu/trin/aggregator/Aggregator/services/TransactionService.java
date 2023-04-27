package edu.trin.aggregator.Aggregator.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.trin.aggregator.Aggregator.models.Flights;
import edu.trin.aggregator.Aggregator.models.MyRequest;
import edu.trin.aggregator.Aggregator.models.Payment;
import edu.trin.aggregator.Aggregator.models.Seat;
import edu.trin.aggregator.Aggregator.models.Transaction;
import edu.trin.aggregator.Aggregator.models.User;

@Service
public class TransactionService {
   
   public TransactionService() {}
   
   public List<Transaction> getAllTransactions(){
    String urlName = System.getenv("Transactions") != null ? System.getenv("Transactions") : "localhost";
    String port = System.getenv("Transactions_Port") != null ? System.getenv("Transactions_Port") : "8082";
    String api = System.getenv("Transactions_api") != null ? System.getenv("Transactions_api") : "add-transaction";
    String url = String.format("http://%s:%s/%s", urlName, port, api);
    RestTemplate restTemplate = new RestTemplate();
    List<Transaction> transactions = restTemplate.getForObject(url, List.class);
    return transactions;
   }

   public ArrayList<String> findTransactionByUsername(String username){
    String urlName = System.getenv("Transactions") != null ? System.getenv("Transactions") : "localhost";
    String port = System.getenv("Transactions_Port") != null ? System.getenv("Transactions_Port") : "8082";
    String api = System.getenv("Transactions_api_get") != null ? System.getenv("Transactions_api_get") : "get-transaction";
    String url = String.format("http://%s:%s/%s/%s", urlName, port, api, username);
    RestTemplate restTemplate = new RestTemplate();
    ArrayList<String> user_transactions = restTemplate.getForObject(url, ArrayList.class);

    return user_transactions; 
   }

   public ResponseEntity<String> createTransaction(Payment payment, String username, Integer num_tickets, String fly_from, String fly_to, String date, Integer flight_no, String seat_choice, Integer price){
    
    String urlName = System.getenv("Flights") != null ? System.getenv("Flights") : "localhost";
    String port = System.getenv("Flights_Port") != null ? System.getenv("Flights_Port") : "8081";
    String api = System.getenv("Flights_api") != null ? System.getenv("Flights_api") : "flights";
    String url = String.format("http://%s:%s/%s/%s/%s/%s/%d/%d", urlName, port, api, fly_from, fly_to, date, flight_no, price);
    RestTemplate restTemplate = new RestTemplate();
    Flights flight = restTemplate.getForObject(url, Flights.class);

    String url2Name = System.getenv("Users") != null ? System.getenv("Users") : "localhost";
    String port2 = System.getenv("Users_Port") != null ? System.getenv("Users_Port") : "8083";
    String api2 = System.getenv("Users_api_get") != null ? System.getenv("Users_api_get") : "users";
    String url2 = String.format("http://%s:%s/%s/%s", url2Name, port2, api2, username);
    RestTemplate restTemplate2 = new RestTemplate();
    User user = restTemplate2.getForObject(url2, User.class);

    if(flight == null){
        return ResponseEntity.ok("THE FLIGHT YOU CHOOSE DOESN'T EXIST");
    }
    if(num_tickets < 1){
        return ResponseEntity.ok("TO PLACE AN ORDER PLEASE INSERT A VALID AMOUNT OF TICKETS");
    }
    if(user == null){
        return ResponseEntity.ok("THE USERNAME PROVIDED DOESN'T EXIST"); 
    }

    //creates and saves new transaction
    Transaction current_transaction = new Transaction(username, flight_no, num_tickets, (double) (flight.getPrice()*num_tickets));
    RestTemplate restTemplate3 = new RestTemplate();
    String url3Name = System.getenv("Transactions") != null ? System.getenv("Transactions") : "localhost";
    String port3 = System.getenv("Transactions_Port") != null ? System.getenv("Transactions_Port") : "8082";
    String api3 = System.getenv("Transactions_api") != null ? System.getenv("Transactions_api") : "add-transaction";
    String url3 = String.format("http://%s:%s/%s", url3Name, port3, api3);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Transaction> request = new HttpEntity(current_transaction, headers);
    restTemplate3.postForEntity(url3, request, String.class);


    //buy seat
    String url4Name = System.getenv("Seats") != null ? System.getenv("Seats") : "localhost";
    String port4 = System.getenv("Seats_Port") != null ? System.getenv("Seats_Port") : "8086";
    String api4 = System.getenv("Seats_api_get") != null ? System.getenv("Seats_api_get") : "seats";
    String url7 = String.format("http://%s:%s/%s/%d/%s", url4Name, port4, api4, flight_no, date);
    RestTemplate restTemplate7 = new RestTemplate();
    Seat seat_transactions = restTemplate7.getForObject(url7, Seat.class);
    
    if(flight.getAvailableSeats() == 0){
        return ResponseEntity.ok("NO SEATS AVAILABLE");
    }
    if(seat_transactions == null){
        return ResponseEntity.ok("NEED TO CALL SEAT DISPLAY FIRST");
    }

    RestTemplate restTemplate6 = new RestTemplate();
    String api5 = System.getenv("Seats_api_update") != null ? System.getenv("Seats_api_update") : "update-seats";
    String url6 = String.format("http://%s:%s/%s/%d/%s/%s/%d", url4Name, port4, api5, flight_no, seat_choice, date, num_tickets);
    
    HttpHeaders headers6 = new HttpHeaders();
    headers6.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

    ResponseEntity<String> responseEntity = restTemplate.exchange(url6, HttpMethod.PUT, requestEntity, String.class);

    String response = responseEntity.getBody();

    if(response.equals("INVALID NUMBER OF SEATS")){
        return ResponseEntity.ok(response);
    }

    if(response.equals("THE CHOSEN SEAT IS NOT AVAILABLE")){
        return ResponseEntity.ok(response);
    }


    //saves the payment
    RestTemplate restTemplate4 = new RestTemplate();
    String url6Name = System.getenv("Payments") != null ? System.getenv("Payments") : "localhost";
    String port6 = System.getenv("Payments_Port") != null ? System.getenv("Payments_Port") : "8084";
    String api6 = System.getenv("Payments_api") != null ? System.getenv("Payments_api") : "api/p1/payments";
    String url4 = String.format("http://%s:%s/%s", url6Name, port6, api6);
    HttpHeaders headers2 = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Payment> request2 = new HttpEntity(payment, headers2);
    restTemplate4.postForEntity(url4, request2, String.class);

    MyRequest notification = new MyRequest(user.getEmail(), current_transaction.toString());

    //sends out email with transaction info
    RestTemplate restTemplate5 = new RestTemplate();
    String url7Name = System.getenv("Notifications") != null ? System.getenv("Notifications") : "localhost";
    String port7 = System.getenv("Notifications_Port") != null ? System.getenv("Notifications_Port") : "8087";
    String api7 = System.getenv("Notifications_api") != null ? System.getenv("Notifications_api") : "notification-info";
    String url5 = String.format("http://%s:%s/%s", url7Name, port7, api7);
    HttpHeaders headers3 = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Payment> request3 = new HttpEntity(notification, headers3);
    ResponseEntity<String> response_notification = restTemplate5.postForEntity(url5, request3, String.class);

    return response_notification;
   }
}
