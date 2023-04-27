package edu.trin.aggregator.Aggregator.models;

public class Payment {

    private long id;
    private String accountNo;
    private String cardHolderName;
    private String cardNo;
    private String cvv;
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
