package com.example.bookstore2.demo.Entity;

import com.example.bookstore2.demo.Builder;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String author;
    private BigDecimal price;
    private String category;
    private String image;
    private int quantity;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Review> review;


    public Book(){

    }

    public Book(Builder builder){
        setId(builder.id);
        setTitle(builder.title);
        setAuthor(builder.author);
        setCategory(builder.category);
        setPrice(builder.price);
        setImage(builder.image);
        setQuantity(builder.quantity);

    }

    public static Builder newBook(){
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }


    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice (BigDecimal unitPrice){
        this.price = unitPrice;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

}



