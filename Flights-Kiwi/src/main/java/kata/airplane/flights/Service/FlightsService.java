package kata.airplane.flights.Service;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.text.NumberFormat.Style;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kata.airplane.flights.Entities.Flights;

@Service
public class FlightsService {

    public List<Flights> getAllFlights(String fly_from, String fly_to, String date) {
        // Create a HttpHeaders object and add the API key as a header
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "YlSzDzxmIW4YddYDm3iqiQ3moqMAMdTS");

        // Create a HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Make the API request using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        
        // Set the limit query parameter to 30
        String urlName = System.getenv("Flights_Kiwi") != null ? System.getenv("Flights_Kiwi") : "api.tequila.kiwi.com/v2";
        String api_call = System.getenv("Kiwi_api_call") != null ? System.getenv("Kiwi_api_call") : "search";
        String url = String.format("https://%s/%s", urlName, api_call);

        URI uri = UriComponentsBuilder.fromUriString(url)
        .queryParam("fly_from", "airport:" + fly_from)
        .queryParam("fly_to", "airport:" + fly_to)
        .queryParam("date_from", date)
        .build()
        .toUri();

        // Make the API request using RestTemplate
        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.GET, entity, JsonNode.class);

        // Get the list of flights from the response body
        JsonNode flights = response.getBody().get("data");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Flights> flight_objects = new ArrayList<>();
        for (JsonNode flight : flights) {
            String formattedDate = "";

            Map<String, Object> flightMap = objectMapper.convertValue(flight, new TypeReference<>() {});

            List<Map<String, Object>> route = (List<Map<String, Object>>) flightMap.get("route");
            Integer flightNo = (Integer) route.get(0).get("flight_no");

            Map<String, Object> availability = (Map<String, Object>) flightMap.get("availability");
            Integer seats = (Integer) availability.get("seats");
            if(seats == null){
                seats = 0;
            }

            String dateString = (String) flightMap.get("local_departure");
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("YYYY-MM-dd  HH:mm");
            
            try {
                Date current_date = inputFormat.parse(dateString);
                formattedDate = outputFormat.format(current_date);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Flights flight_object = new Flights(seats, flightNo, (String) flightMap.get("flyFrom"), (String) flightMap.get("flyTo"), formattedDate, (Integer) flightMap.get("price"));
            if(!flight_objects.contains(flight_object)){
                flight_objects.add(flight_object);
            }
        }

        flight_objects.sort(Comparator.comparing(Flights::getLocalDeparture));
        
        // Extract the top 15 flights by price
        List<Flights> top15Flights = flight_objects.stream().limit(15).collect(Collectors.toList());
        return top15Flights;
    }

    public Flights getSpecificFlight(String fly_from, String fly_to, String date, Integer flight_no, Integer price) {
        String formattedDate = "";
        List<Flights> flights = getAllFlights(fly_from, fly_to, date);

        SimpleDateFormat inputFormat = new SimpleDateFormat("YYYY-MM-dd  HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        try {
            Date current_date = inputFormat.parse(date);
            formattedDate = outputFormat.format(current_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(formattedDate);

        for (Flights flight : flights) {
            if(flight.getFlightNo().equals(flight_no) && flight.getPrice().equals(price) && flight.getLocalDeparture().contains(formattedDate)){
                return flight;
            }
        }
        return null;
    }

}
