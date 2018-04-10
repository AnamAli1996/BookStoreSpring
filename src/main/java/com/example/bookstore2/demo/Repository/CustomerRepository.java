package com.example.bookstore2.demo.Repository;

import com.example.bookstore2.demo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByEmailAndPassword(String email, String password);
}
