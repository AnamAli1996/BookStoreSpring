package com.example.bookstore2.demo.Controller;

import com.example.bookstore2.demo.Entity.*;
import com.example.bookstore2.demo.Repository.BookRepository;
import com.example.bookstore2.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ShoppingCartController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/shoppingCart/addBook/{id}")
    public ModelAndView addBookToCart(@PathVariable("id") int bookId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("cart");
        Book book = bookRepository.findById(bookId);
        Cart cart = new Cart();
        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        if(list == null){
            list = new ArrayList<Cart>();

        }
        if(book != null){
            cart.ToCart(book);
            BigDecimal total = addToCart(list, cart);
            modelAndView.addObject("total", total);
            session.setAttribute("cart", list);
        }

        modelAndView.addObject("listCart", list);
        return modelAndView;

    }

    private BigDecimal addToCart(List<Cart> list, Cart cart){
        BigDecimal total = new BigDecimal(0);
        boolean isExit = false;
        for(Cart c: list){
            if(c.equals(cart)) {
                c.setQuantity(c.getQuantity() + 1);
                isExit = true;
            }
            total = total.add(c.getPrice().multiply(new BigDecimal(c.getQuantity())));

        }if(isExit == false){
            list.add(cart);
            total = total.add(cart.getPrice().multiply(new BigDecimal(cart.getQuantity())));
        }
        return total;

    }

    @PostMapping("shoppingCart/pay")
    public ModelAndView payForCart(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("paymentSuccess");
        return modelAndView;

    }
}
