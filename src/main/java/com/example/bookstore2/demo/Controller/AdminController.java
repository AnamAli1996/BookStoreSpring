package com.example.bookstore2.demo.Controller;

import com.example.bookstore2.demo.Entity.Book;
import com.example.bookstore2.demo.Entity.Orders;
import com.example.bookstore2.demo.Entity.User;
import com.example.bookstore2.demo.Repository.BookRepository;
import com.example.bookstore2.demo.Repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserRepository adminRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("admin/home")
    public ModelAndView adminHome(){
        ModelAndView modelAndView = new ModelAndView("AdminHome");
        return modelAndView;
    }


    @GetMapping("admin/book/allbooks")
    public ModelAndView displayAllBook(){
        ModelAndView modelAndView = new ModelAndView("BookList");
        List<Book> allBooks = bookRepository.findAll();
        modelAndView.addObject("books", allBooks);
        return modelAndView;
    }

    @GetMapping("/admin/EditBook/{id}")
    public ModelAndView editBook(@PathVariable("id") int bookId, HttpSession session){
        Book book = bookRepository.findById(bookId);
        ModelAndView modelAndView = new ModelAndView("EditBook");
        modelAndView.addObject("book", book);
        return modelAndView;

    }

    @PostMapping("/admin/EditBook/{id}")
    public ModelAndView editBook(@PathVariable("id") int bookId, @ModelAttribute Book book){
        bookRepository.save(book);
        ModelAndView modelAndView = new ModelAndView("AdminHome");
        return modelAndView;

    }

    @GetMapping("/admin/allcustomers")
    public ModelAndView viewAllCustomer ()    {
        List<User> allUsers = userRepository.findAll();
        List<User> customerUser = new ArrayList<>();
        for(User u: allUsers){
            if(u.getRole().equalsIgnoreCase("customer")){
                customerUser.add(u);
            }
        }

        ModelAndView modelAndView = new ModelAndView("CustomerList");
        modelAndView.addObject("customers", customerUser);
        return modelAndView;
    }

    @GetMapping("/admin/viewOrders/{id}")
    public ModelAndView viewPurchaseHistory(@PathVariable("id") int userId){
       User u =  userRepository.findById(userId);
       List<Orders> orders = new ArrayList<>();
       List<Book> books = new ArrayList<>();
        for(Orders o: u.getOrders()){
            orders.add(o);
            books = o.getBookList();
        }
        ModelAndView modelAndView = new ModelAndView("PurchaseList");
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("books", books);
        return modelAndView;
    }


}
