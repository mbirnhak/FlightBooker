package com.kata.seating.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.seating.Entity.Seat;
import com.kata.seating.dao.SeatRepository;

@Service
public class SeatService {
    private SeatRepository repository;

    @Autowired
    public SeatService(SeatRepository repository) {
        this.repository = repository;
    }
    
    public List<Seat> getAllSeats(){
        List<Seat> seats = repository.findAll();
        if(seats.isEmpty()) {
            return null;
        }
        return seats;
    }

    public Seat getSeatByFlightNum_Date(Integer flight_no, String date){
        List<Seat> seats = getAllSeats();

        if(seats != null){
            for(int i = 0; i < seats.size(); i++){
                if(seats.get(i).getFlight_no().equals(flight_no) && seats.get(i).getDate().contains(date)){
                    return seats.get(i);
                }
            }
        }
        return null;
    }

    public String addSeat(Seat seat){
        repository.save(seat);

        if(repository.findAll().contains(seat)){
            return "ADDED";
        }
        return "NOT ADDED";
    }

    public String updateSeat(Integer flight_no, String seat_choice, String date, Integer num_tickets){
        Seat seat = this.getSeatByFlightNum_Date(flight_no, date);
        Integer num_seat = seat.getAvailable_seats();

        if(seat != null){
            seat.setAvailable_seats(num_seat - num_tickets);
            String[] myArray = seat_choice.split(",");

            if(myArray.length != num_tickets){
                return "INVALID NUMBER OF SEATS";
            }

            String string_array = seat.serializeSeats(seat.getSeats_display());

            for(String element : myArray){
                if(!(string_array.contains(element))){
                    return "THE CHOSEN SEAT IS NOT AVAILABLE";
                }
            }

            for (String str : myArray) {
                string_array = string_array.replace(str, "X");
            }

            String[][] array = seat.deserializeSeats(string_array);
            seat.setSeats_display(array);
            repository.save(seat);


            boolean contains_seat = false;
            List<String[]> list = Arrays.asList(seat.getSeats_display());
            for (String[] row : list) {
                if (Arrays.asList(row).contains(seat_choice)) {
                    contains_seat = true;
                }
            }
            
            if(seat.getAvailable_seats().equals(num_seat - num_tickets) && contains_seat == false){
                return "YOU HAVE SUCCESSFULY UPDATED THE NUMBER OF AVAILABLE SEATS";
            }
        }
        return "OPERATION FAILED";
    }
}
