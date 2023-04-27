package edu.trin.aggregator.Aggregator.models;

public class Flights {
    
    public Integer available_seats;
    private Integer flight_no;
    private String flyFrom;
    private String flyTo;
    private String local_departure;
    private Integer price;



    public Flights (){}

    public Flights (Integer available_seats, Integer flight_no, String flyFrom, String flyTo, String local_departure, Integer price){
        this.available_seats = available_seats;
        this.flight_no = flight_no;
        this.flyFrom = flyFrom;
        this.flyTo = flyTo;
        this.local_departure = local_departure;
        this.price = price;
    }

    public String getFlyFrom(){
        return flyFrom;
    }

    public void setFlyFrom(String flyFrom){
        this.flyFrom = flyFrom;
    }

    public String getFlyTo(){
        return flyTo;
    }

    public void setFlyTo(String flyTo){
        this.flyTo = flyTo;
    }

    public Integer getPrice(){
        return price;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public Integer getFlightNo(){
        return flight_no;
    }

    public void setFlightNo(Integer flight_no){
        this.flight_no = flight_no;
    }

    public String getLocalDeparture(){
        return local_departure;
    }

    public void setLocalDeparture(String local_departure){
        this.local_departure = local_departure;
    }

    public Integer getAvailableSeats(){
        return available_seats;
    }

    public void setAvailableSeats(Integer available_seats){
        this.available_seats = available_seats;
    }
    

    @Override
    public String toString() {
        return "{" +
                "Available Seats=" + available_seats +
                ", Flight Number=" + flight_no +
                ", From='" + flyFrom + '\'' +
                ", To='" + flyTo + '\'' +
                ", Departure Date & Time='" + local_departure + '\'' +
                ", Price=" + price +
                '}';
    }
}
