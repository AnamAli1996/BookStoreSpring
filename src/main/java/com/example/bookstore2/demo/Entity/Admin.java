package com.example.bookstore2.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin extends Customer{

    public Admin(){
       super();
       setType("Admin");
    }



}
