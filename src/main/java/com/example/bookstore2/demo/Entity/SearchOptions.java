package com.example.bookstore2.demo.Entity;

public class SearchOptions {
    private int optionId;
    private String name;


    public SearchOptions(){

    }

    public SearchOptions(int optionId, String name){
        this.optionId = optionId;
        this.name = name;
    }

    public int getOptionId() {
        return optionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }


}
