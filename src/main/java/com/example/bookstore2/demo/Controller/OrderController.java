package com.example.bookstore2.demo.Controller;

import com.example.bookstore2.demo.Entity.*;
import com.example.bookstore2.demo.OrderDao;
import com.example.bookstore2.demo.Repository.CustomerRepository;
import com.example.bookstore2.demo.Repository.OrderDetailsRepository;
import com.example.bookstore2.demo.Repository.OrderRepository;
import com.example.bookstore2.demo.Utils.CartUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(value="/order/create")

        public ModelAndView createOrder(@ModelAttribute("order")Order order, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("editOrder");
        List<Cart> cartList = (List<Cart>) session.getAttribute("cart");
        if(session.getAttribute("cart") == null){
            return new ModelAndView("redirect:/customer/login");
        }
        return modelAndView;
    }

    @RequestMapping(value="/order/save")
    public ModelAndView saveOrder(@ModelAttribute("order") Order order, HttpSession session){
        ModelAndView mav=new ModelAndView("orderSuccess");
        List<Cart> cartList =(List<Cart>) session.getAttribute("cart");
        if(cartList==null){
            return new ModelAndView("redirect:/customer/login");
        }
        List<OrderDetail> detailList = CartUtils.ToOrderDetail(cartList);
        order = new Order();
        order.setStatus(1);
        order.setTotal(CartUtils.getTotal(cartList));
        order.setEmail("");

        String email = (String) session.getAttribute("loggedInUser");
        Card card;


        User u =  customerRepository.findByEmail(email);

        if(u.getPaymentMethod().equals("Credit Card")){
            card = new CreditCard();
        }else
            card = new DebitCard();

        mav.addObject("card", card);


        for(OrderDetail orderDetail: detailList){
            orderDetailsRepository.save(orderDetail);
        }
        return mav;
    }
}
