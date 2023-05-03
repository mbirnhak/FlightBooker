package edu.trincoll.transactionhistory.models;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Transaction {

    @Id
    private String Id;

    @Indexed(unique = true)
    private String username;

    private Integer flight_id;
    private Integer num_tickets_bought;
    private Date date_of_order;
    private double price;

    public Transaction() {}

    public Transaction(String username, Integer flight_id, Integer num_tickets_bought, double price) {
        this.username = username;
        this.flight_id = flight_id;
        this.num_tickets_bought = num_tickets_bought;
        this.date_of_order = new Date();
        this.price = price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public Integer getNum_tickets_bought() {
        return num_tickets_bought;
    }

    public void setNum_tickets_bought(Integer num_tickets_bought) {
        this.num_tickets_bought = num_tickets_bought;
    }

    public Date getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(Date date_of_order) {
        this.date_of_order = date_of_order;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID:" + Id + ", USERNAME:" + username + ", FLIGHT ID:" + flight_id + ", NUMBER OF TICKETS BOUGHT:"
                + num_tickets_bought + ", DATE OF ORDER:" + date_of_order + ", PRICE:$" + price;
    }
}
