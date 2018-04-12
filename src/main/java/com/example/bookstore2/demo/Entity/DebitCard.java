package com.example.bookstore2.demo.Entity;

public class DebitCard extends Card{

    @Override
    public void setCardType(String cardType) {
        super.setCardType("Debit Card");
    }
}
