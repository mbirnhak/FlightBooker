package com.kata.seating.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Document
public class Seat {

    @Id
    private String id;
    private Integer flight_no;
    private String date;
    private String seats_display;
    private Integer available_seats;

    public Seat() {};

    public Seat(Integer flight_no, String date, String[][] seats_display, Integer available_seats) {
        this.flight_no = flight_no;
        this.date = date;
        this.seats_display = serializeSeats(seats_display);
        this.available_seats = available_seats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(Integer flight_no) {
        this.flight_no = flight_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[][] getSeats_display() {
        return deserializeSeats(seats_display);
    }

    public void setSeats_display(String[][] seats_display) {
        this.seats_display = serializeSeats(seats_display);
    }

    public Integer getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Integer available_seats) {
        this.available_seats = available_seats;
    }

    public String serializeSeats(String[][] seats) {
        if (seats == null || seats.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                sb.append(seats[i][j]);
                if (j != seats[i].length - 1) {
                    sb.append(",");
                }
            }
            sb.append(";");
        }
        return sb.toString();
    }

    public String[][] deserializeSeats(String seatsString) {
        if (seatsString == null || seatsString.isEmpty()) {
            return new String[0][0];
        }
        String[] rows = seatsString.split(";");
        String[][] seats = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split(",");
            seats[i] = new String[cols.length];
            for (int j = 0; j < cols.length; j++) {
                seats[i][j] = cols[j];
            }
        }
        return seats;
    }
}