package kata.airplane.flights.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kata.airplane.flights.Entities.Flights;
import kata.airplane.flights.Service.FlightsService;

@RestController
public class FlightsRestController {
    private FlightsService service;

    @Autowired
    public FlightsRestController(FlightsService service) {
        this.service = service;
    }

    @GetMapping("/flights/{fly_from}/{fly_to}/{date}")
    public List<Flights> getFlights(@PathVariable String fly_from, @PathVariable String fly_to, @PathVariable String date) {
        List<Flights> flight_list = service.getAllFlights(fly_from, fly_to, date);
        return flight_list;
    }

    //can be used to choose flight based on flight number and price
    @GetMapping("/flights/{fly_from}/{fly_to}/{date}/{flight_no}/{price}")
    public Flights findFlight(@PathVariable String fly_from, @PathVariable String fly_to, @PathVariable String date, @PathVariable Integer flight_no, @PathVariable Integer price){
        Flights flight = service.getSpecificFlight(fly_from, fly_to, date, flight_no, price);
        return flight;
    }
}
