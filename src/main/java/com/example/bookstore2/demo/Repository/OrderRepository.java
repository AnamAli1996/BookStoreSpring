package com.example.bookstore2.demo.Repository;

import com.example.bookstore2.demo.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
