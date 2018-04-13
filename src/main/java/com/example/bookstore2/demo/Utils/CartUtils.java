package com.example.bookstore2.demo.Utils;

import com.example.bookstore2.demo.Entity.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartUtils {

    public static BigDecimal getTotal(List<Cart> cartList){
        BigDecimal total = new BigDecimal(0);
        for (Cart cart:cartList) {
            total = total.add(new BigDecimal(cart.getQuantity() * cart.getPrice().longValue()));
        }
        return total;
    }

}

