package edu.trincoll.transactionhistory.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.util.Date;
import jakarta.persistence.Id;

@Entity
@Table(name = "transactions")
public class Transaction {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id; 
    private String username;
    private Integer flight_id;
    private Integer num_tickets_bought;
    private Date date_of_order;
    private Double total_price;

    public Transaction() {}

    public Transaction(String username, Integer flight_id, Integer num_tickets_bought, Double price) {
        this.username = username;
        this.flight_id = flight_id;
        this.num_tickets_bought = num_tickets_bought;
        this.date_of_order = new Date();
        this.total_price = price;
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

    public Double getPrice() {
        return total_price;
    }

    public void setPrice(Double price) {
        this.total_price = price;
    }

    @Override
    public String toString() {
        return "USERNAME:" + username + ", FLIGHT ID:" + flight_id + ", NUMBER OF TICKETS BOUGHT:"
                + num_tickets_bought + ", DATE OF ORDER:" + date_of_order + ", TOTAL PRICE:$" + total_price;
    }
}
