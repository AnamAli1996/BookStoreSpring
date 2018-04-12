package com.example.bookstore2.demo.Utils;

import com.example.bookstore2.demo.Entity.Cart;
import com.example.bookstore2.demo.Entity.OrderDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartUtils {
    public static List<OrderDetail> ToOrderDetail(List<Cart> cartList){
        List<OrderDetail> detailList=new ArrayList<OrderDetail>();
        OrderDetail orderDetail;
        for (Cart cart:cartList) {
            orderDetail=new OrderDetail();
            orderDetail.setBookid(cart.getId());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setPrice(cart.getPrice());
            orderDetail.setStatus(1);
            detailList.add(orderDetail);
        }
        return detailList;
    }

    public static BigDecimal getTotal(List<Cart> cartList){
        BigDecimal total = new BigDecimal(0);
        for (Cart cart:cartList) {
            total = total.add(new BigDecimal(cart.getQuantity() * cart.getPrice().longValue()));
        }
        return total;
    }

}

