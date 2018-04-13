package com.example.bookstore2.demo.Controller;

import com.example.bookstore2.demo.Entity.*;
import com.example.bookstore2.demo.Repository.BookRepository;
import com.example.bookstore2.demo.Repository.UserRepository;
import com.example.bookstore2.demo.Repository.OrderRepository;
import com.example.bookstore2.demo.Utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;


    @RequestMapping(value="/order/create")

        public ModelAndView createOrder(@ModelAttribute("order")Orders order, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("editOrder");
        List<Cart> cartList = (List<Cart>) session.getAttribute("cart");
        List<Book> booksInCart = new ArrayList<>();
        order = new Orders();
        order.setStatus(1);
        order.setTotal(CartUtils.getTotal(cartList));
        for(Cart cart:cartList){
            cart.setQuantity(cart.getQuantity()-1);
            Book book=  bookRepository.findByTitle(cart.getTitle());
            book.setQuantity(book.getQuantity()-1);
            bookRepository.save(book);
            booksInCart.add(book);
        }

        order.setBookList(booksInCart);
        String emailUser = (String) session.getAttribute("loggedInUser");
        User u = userRepository.findByEmail(emailUser);
        u.getOrders().add(order);
        orderRepository.save(order);

        if(session.getAttribute("cart") == null){
            return new ModelAndView("redirect:/customer/login");
        }
        return modelAndView;
    }

    @RequestMapping(value="/order/save")
    public ModelAndView saveOrder(@ModelAttribute("order") Orders order, HttpSession session){
        ModelAndView mav=new ModelAndView("orderSuccess");
        List<Cart> cartList =(List<Cart>) session.getAttribute("cart");
        if(cartList==null){
            return new ModelAndView("redirect:/customer/login");
        }



        order.setTotal(CartUtils.getTotal(cartList));

        String email = (String) session.getAttribute("loggedInUser");
        Card card;


        User u =  userRepository.findByEmail(email);

        if(u.getPaymentMethod().equals("Credit Card")){
            card = new CreditCard();
        }else
            card = new DebitCard();

        mav.addObject("card", card);





        return mav;
    }
}
