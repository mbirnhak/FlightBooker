package edu.trin.aggregator.Aggregator.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.trin.aggregator.Aggregator.models.Flights;
import edu.trin.aggregator.Aggregator.models.Payment;
import edu.trin.aggregator.Aggregator.models.Transaction;
import edu.trin.aggregator.Aggregator.models.User;
import edu.trin.aggregator.Aggregator.services.FlightsService;
import edu.trin.aggregator.Aggregator.services.SeatsService;
import edu.trin.aggregator.Aggregator.services.UserService;
import edu.trin.aggregator.Aggregator.services.TransactionService;

@RestController
public class AggregatorRestController {
    @Autowired
    private UserService userservice;

    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users = userservice.findAll();
        /**List<String> stringArray = new ArrayList<String>();
        
        if (users.size() == 0){
            String str = "THERE ARE NO USERS AT THE MOMENT";
            stringArray.add(str);
            return stringArray;
        }
        for (int i=0;i<users.size();i++){
            String username = users.get(i).getUsername();
            String password = users.get(i).getPassword();
            String str = "USERNAME: "+username+"\nPASSWORD: "+password+"\n";
            stringArray.add(str);
        }*/
        return users;
    }

    @GetMapping("/users/{username1}")
    public String findUser(@PathVariable String username1){
        User user = userservice.findUser(username1);
        if(user == null){
            return "USER NOT FOUND";
        }
        return "USERNAME: " + user.getUsername() + "\nPASSWORD: " + user.getPassword() + "\n";
    }

    @PostMapping(value = "/add-user", consumes = {"application/json"}, produces = {"application/json"} )
    public String addUser(@RequestBody User user){
        return userservice.addUser(user);
    }

    @PutMapping("/update-user/{password}")
    public ResponseEntity<String> updateUserPassword(@RequestBody User user, @PathVariable String password){
        return userservice.updatePassword(user, password);
    }

    @DeleteMapping("/delete-user/{username}/{password}")
    public ResponseEntity<String> deleteUser( @PathVariable String username ,@PathVariable String password){
        return userservice.deleteUser(username, password);
    }

    @Autowired
    private FlightsService flightservice;

    @GetMapping("/flights/{fly_from}/{fly_to}/{date}")
    public String getFlights(@PathVariable String fly_from, @PathVariable String fly_to, @PathVariable String date) {
        return flightservice.getFlights(fly_from, fly_to, date);
    }

    //can be used to choose flight based on id
    @GetMapping("/flights/{fly_from}/{fly_to}/{date}/{flight_no}/{price}")
    public String findFlight(@PathVariable String fly_from, @PathVariable String fly_to, @PathVariable String date, @PathVariable Integer flight_no, @PathVariable Integer price){
        Flights flight = flightservice.getFlightBySpecifics(fly_from, fly_to, date, flight_no, price);
        if(flight == null){
            return "THE FLIGHT DOESN'T EXIST";
        }
        return flight.toString();
    }

    @Autowired
    private TransactionService transactionservice;

    @GetMapping("/get-transactions")
    public List<Transaction> getTransaction(){
        return transactionservice.getAllTransactions();
    }

    @GetMapping("/get-transaction/{username}")
    public ArrayList<String> findTransactionByUsername(@PathVariable String username){
        return transactionservice.findTransactionByUsername(username);
    }

    @Autowired
    private SeatsService seatsservice;

    @GetMapping("/get-seating/{fly_from}/{fly_to}/{date}/{flight_no}/{price}")
    public String getSeatDisplay(@PathVariable String fly_from, @PathVariable String fly_to, @PathVariable String date, @PathVariable Integer flight_no, @PathVariable Integer price){
        return seatsservice.DisplaySeats(fly_from, fly_to, date, flight_no, price);
    }

    @PostMapping("/new-order/{username}/{num_tickets}/{fly_from}/{fly_to}/{date}/{flight_no}/{seat_choice}/{price}")
    public ResponseEntity<String> newTransaction(@RequestBody Payment payment,@PathVariable String username, @PathVariable Integer num_tickets, @PathVariable String fly_from, @PathVariable String fly_to, @PathVariable String date, @PathVariable Integer flight_no, @PathVariable String seat_choice, @PathVariable Integer price){
        return transactionservice.createTransaction(payment, username, num_tickets, fly_from, fly_to, date, flight_no, seat_choice, price);
    }

}
