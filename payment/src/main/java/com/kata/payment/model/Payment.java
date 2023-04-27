package com.kata.payment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "account_no")
    private String accountNo;
    
    @Column(name = "card_holder_name")
    private String cardHolderName;
    
    @Column(name = "card_no")
    private String cardNo;
    
    @Column(name = "cvv")
    private String cvv;
    
    @Column(name = "exp_date")
    private String expDate;

    public Payment() {}

    public Payment(String accountNo, String cardHolderName, String cardNo, String cvv, String expDate) {
        this.accountNo = accountNo;
        this.cardHolderName = cardHolderName;
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.expDate = expDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}

