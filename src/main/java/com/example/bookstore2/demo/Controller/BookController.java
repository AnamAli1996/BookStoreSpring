package com.example.bookstore2.demo.Controller;

import com.example.bookstore2.demo.Entity.Book;
import com.example.bookstore2.demo.Entity.Cart;
import com.example.bookstore2.demo.Entity.Review;
import com.example.bookstore2.demo.Entity.User;
import com.example.bookstore2.demo.Repository.BookRepository;
import com.example.bookstore2.demo.Repository.CustomerRepository;
import com.example.bookstore2.demo.Repository.ReviewRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/book/new")
    public ModelAndView bookForm()
    {
        ModelAndView modelAndView = new ModelAndView();
        Book book = Book.newBook().build();
        modelAndView.addObject("book", book);
        modelAndView.setViewName("BookForm");

        return modelAndView;
    }

    @PostMapping("/book/new")
    public String bookSubmit(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "result";
    }

    @RequestMapping(value = "/book/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "search", required = false) String q, Model model){
        List<Book> searchResults;
        searchResults = bookRepository.findByTitleOrAuthor(q, q);
        if(searchResults.size() == 0){
            searchResults = bookRepository.findByCategory(q);
        }

        model.addAttribute("search", searchResults);
        return "Welcome";
    }

    @GetMapping("/book/review/{id}")
    public ModelAndView addReview(@PathVariable("id") int bookId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("ReviewTemplate");
        Book book = bookRepository.findById(bookId);
        session.setAttribute("book", book.getId());
        Review review = new Review();
        modelAndView.addObject("book", book);
        modelAndView.addObject("review", review);
        return modelAndView;
    }

    @GetMapping("/book/ViewReview/{id}")
    public ModelAndView ViewReview(@PathVariable("id") int bookId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("Reviews");
        Book book = bookRepository.findById(bookId);
        List<Review> reviews = book.getReview();




        modelAndView.addObject("book", book);
        modelAndView.addObject("reviews", reviews);
        return modelAndView;
    }

    @PostMapping("/book/addReview")
    public String customerSubmit(@ModelAttribute Review review, HttpSession session) {
        int bookId = (Integer) session.getAttribute("book");

        Book book = bookRepository.findById(bookId);
        String email = (String) session.getAttribute("loggedInUser");

        User user = customerRepository.findByEmail(email);
        review.setUser(user);
        book.getReview().add(review);
        bookRepository.save(book);
        reviewRepository.save(review);
        return "Welcome.html";
    }


}
