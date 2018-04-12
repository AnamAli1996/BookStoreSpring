package com.example.bookstore2.demo.Repository;

import com.example.bookstore2.demo.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
