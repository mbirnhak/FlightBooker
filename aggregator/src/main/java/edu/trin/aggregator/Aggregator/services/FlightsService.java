package edu.trin.aggregator.Aggregator.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.trin.aggregator.Aggregator.models.Flights;

@Service
public class FlightsService {

    public List<Flights> getFlights(String fly_from, String fly_to, String date){
        String urlName = System.getenv("Flights") != null ? System.getenv("Flights") : "localhost";
        String port = System.getenv("Flights_Port") != null ? System.getenv("Flights_Port") : "8089";
        String api = System.getenv("Flights_api") != null ? System.getenv("Flights_api") : "flights";
        String url = String.format("http://%s:%s/%s/%s/%s/%s", urlName, port, api, fly_from, fly_to, date);
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        List<Flights> flights = restTemplate.getForObject(url, List.class);
        return flights;
    }

    public Flights getFlightBySpecifics(String fly_from, String fly_to, String date, Integer flight_no, Integer price){
        String urlName = System.getenv("Flights") != null ? System.getenv("Flights") : "localhost";
        String port = System.getenv("Flights_Port") != null ? System.getenv("Flights_Port") : "8089";
        String api = System.getenv("Flights_api") != null ? System.getenv("Flights_api") : "flights";
        String url = String.format("http://%s:%s/%s/%s/%s/%s/%d/%d", urlName, port, api, fly_from, fly_to, date, flight_no, price);
        RestTemplate restTemplate = new RestTemplate();
        Flights flight = restTemplate.getForObject(url, Flights.class);
        return flight;
    }
}