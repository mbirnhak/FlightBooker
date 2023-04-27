package com.kata.notifications.Entity;

public class MyRequest {
    
    private String recipientEmail;
    private String paymentInfo;

    public MyRequest (){}

    public MyRequest (String recipientEmail, String paymentInfo){
        this.recipientEmail = recipientEmail;
        this.paymentInfo = paymentInfo;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
