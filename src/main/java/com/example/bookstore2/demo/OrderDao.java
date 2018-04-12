package com.example.bookstore2.demo;

import com.example.bookstore2.demo.Entity.Order;
import com.example.bookstore2.demo.Entity.OrderDetail;

import java.util.List;

public interface OrderDao {
    public void create(Order b, List<OrderDetail> orderDetails);
}
