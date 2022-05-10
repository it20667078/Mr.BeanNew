package com.example.savvycoffee.models;

public class Payment {
    String cardHoldersName;
    String cardType;
    String cardNumber;
    String cvc;
    String expMonth;
    String expYear;

    public Payment(String cardHoldersName, String cardType, String cardNumber, String cvc, String expMonth, String expYear) {
        this.cardHoldersName = cardHoldersName;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public Payment() {

    }

    public String getCardHoldersName() {
        return cardHoldersName;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }
}
