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

    public String DisplaySeats(String fly_from, String fly_to, String date, Integer flight_no, Integer price){

        String urlName = System.getenv("Flights") != null ? System.getenv("Flights") : "localhost";
        String port = System.getenv("Flights_Port") != null ? System.getenv("Flights_Port") : "8081";
        String api = System.getenv("Flights_api") != null ? System.getenv("Flights_api") : "flights";
        String url = String.format("http://%s:%s/%s/%s/%s/%s/%d/%d", urlName, port, api, fly_from, fly_to, date, flight_no, price);
        RestTemplate restTemplate = new RestTemplate();
        Flights flight = restTemplate.getForObject(url, Flights.class);
        if(flight == null){
            return "THE FLIGHT DOESN'T EXIST";
        }


        Integer available_seats = flight.getAvailableSeats();
        String[][] seat_arr = new String[(int) Math.ceil((double) available_seats/6)][6];

        String url2Name = System.getenv("Seats") != null ? System.getenv("Seats") : "localhost";
        String port2 = System.getenv("Seats_Port") != null ? System.getenv("Seats_Port") : "8086";
        String api2 = System.getenv("Seats_api_get") != null ? System.getenv("Seats_api_get") : "seats";
        String url2 = String.format("http://%s:%s/%s/%d/%s", url2Name, port2, api2, flight_no, date);
        RestTemplate restTemplate2 = new RestTemplate();
        Seat seat_transactions = restTemplate2.getForObject(url2, Seat.class);
        

        if(available_seats == 0){
            return "NO SEATS AVAILABLE";
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

            Seat seat = new Seat(flight_no, date, seat_arr, flight.getAvailableSeats());
            HttpEntity<Seat> request3 = new HttpEntity(seat, headers3);
            restTemplate3.postForEntity(url3, request3, String.class);

            StringBuilder sb = new StringBuilder();
            for (Object[] row : seat_arr) {
                sb.append("[");
                for (Object element : row) {
                    sb.append(element).append(", ");
                }
                sb.setLength(sb.length() - 2); // Remove the last ", " separator
                sb.append("]\n"); // Add a newline character at the end of each row
            }
            return sb.toString();
        }

        StringBuilder sb = new StringBuilder();
        for (Object[] row : seat_transactions.getSeats_display()) {
            sb.append("[");
            for (Object element : row) {
                sb.append(element).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove the last ", " separator
            sb.append("]\n"); // Add a newline character at the end of each row
        }
        return sb.toString();
    }
}
