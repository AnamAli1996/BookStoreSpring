package com.example.bookstore2.demo.Repository;

import com.example.bookstore2.demo.Entity.Order;
import com.example.bookstore2.demo.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer>{}

