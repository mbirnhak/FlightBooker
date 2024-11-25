package edu.trin.aggregator.Aggregator.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.trin.aggregator.Aggregator.models.Flights;
import edu.trin.aggregator.Aggregator.models.Seat;

@Service
public class SeatsService {

    public String[][] DisplaySeats(String fly_from, String fly_to, String date, Integer flight_no, Integer price, Integer available_seats){

        String[][] seat_arr = new String[(int) Math.ceil((double) available_seats/6)][6];

        String url2Name = System.getenv("Seats") != null ? System.getenv("Seats") : "localhost";
        String port2 = System.getenv("Seats_Port") != null ? System.getenv("Seats_Port") : "8086";
        String api2 = System.getenv("Seats_api_get") != null ? System.getenv("Seats_api_get") : "seats";
        String url2 = String.format("http://%s:%s/%s/%d/%s", url2Name, port2, api2, flight_no, date);
        RestTemplate restTemplate2 = new RestTemplate();
        Seat seat_transactions = restTemplate2.getForObject(url2, Seat.class);
        

        if(available_seats == 0){
            return seat_arr;
        }

        if(seat_transactions == null){
            RestTemplate restTemplate3 = new RestTemplate();
            String api3 = System.getenv("Seats_api_add") != null ? System.getenv("Seats_api_add") : "add-seats";
            String url3 = String.format("http://%s:%s/%s", url2Name, port2, api3);
            HttpHeaders headers3 = new HttpHeaders();
            headers3.setContentType(MediaType.APPLICATION_JSON);

            ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
            for(int i = 0; i < seat_arr.length; i++){
                for(int j = 0; j < seat_arr[i].length && available_seats != 0; j++){
                    String current_letter = alphabet.get(j);
                    seat_arr[i][j] = (i + 1) + current_letter;
                    available_seats--;
                }
            }

            for(int i = 0; i < seat_arr.length; i++){
                for(int j = 0; j < seat_arr[i].length; j++){
                    if(seat_arr[i][j] == null){
                        seat_arr[i][j] = "X";
                    } 
                }
            }

            Seat seat = new Seat(flight_no, date, seat_arr, available_seats);
            HttpEntity<Seat> request3 = new HttpEntity(seat, headers3);
            restTemplate3.postForEntity(url3, request3, String.class);

            return seat_arr;
        }

        return seat_transactions.getSeats_display();
    }
}
