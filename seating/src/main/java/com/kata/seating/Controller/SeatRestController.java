package com.kata.seating.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kata.seating.Entity.Seat;
import com.kata.seating.Service.SeatService;

@RestController
public class SeatRestController {

    @Autowired
    private SeatService seatservice;

    @GetMapping("/seats")
    public List<Seat> findAllSeats(){
        return seatservice.getAllSeats();
    }

    @GetMapping("/seats/{flight_no}/{date}")
    public Seat findSeat(@PathVariable Integer flight_no, @PathVariable String date){
        return seatservice.getSeatByFlightNum_Date(flight_no, date);
    }

    @PostMapping(value = "/add-seats", consumes = {"application/json"})
    public String createUser(@RequestBody Seat seat){
        return seatservice.addSeat(seat);
    }

    @PutMapping("/update-seats/{flight_no}/{seat_choice}/{date}/{num_tickets}")
    public String updateUserPassword(@PathVariable Integer flight_no, @PathVariable String seat_choice, @PathVariable String date, @PathVariable Integer num_tickets){
        return seatservice.updateSeat(flight_no, seat_choice, date, num_tickets);
    }
}
