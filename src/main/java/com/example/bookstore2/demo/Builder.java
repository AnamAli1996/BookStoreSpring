package com.example.bookstore2.demo;

import com.example.bookstore2.demo.Entity.Book;

public final class Builder {
    public int id;
    public String title;
    public String author, category, image;
    public double price;
    public int quantity;

    public Builder(){

    }

    public Builder id(int id){
        this.id = id;
        return this;
    }

    public Builder title(String title){
        this.title = title;
        return this;
    }

    public Builder author(String author){
        this.author = author;
        return this;
    }
    public Builder category(String category){
        this.category = category;
        return this;
    }
    public Builder image(String image) {
        this.image = image;
        return this;

    }

    public Book build(){
        return new Book(this);
    }






}
