package com.example.bookstore2.demo.Controller;

import com.example.bookstore2.demo.Entity.Book;
import com.example.bookstore2.demo.Entity.Review;
import com.example.bookstore2.demo.Entity.User;
import com.example.bookstore2.demo.Repository.BookRepository;
import com.example.bookstore2.demo.Repository.UserRepository;
import com.example.bookstore2.demo.Repository.ReviewRepository;
import com.example.bookstore2.demo.Strategy.Context;
import com.example.bookstore2.demo.Strategy.SearchByAuthor;
import com.example.bookstore2.demo.Strategy.SearchByCategory;
import com.example.bookstore2.demo.Strategy.SearchByTitle;
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
    private UserRepository userRepository;

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
    public String search(@RequestParam(value = "search", required = false) String q,
                         @RequestParam(value = "testOrder") String searchOptions,
                         Model model){
        List<Book> searchResults = new ArrayList<>();

        Context context;
        if(searchOptions.equalsIgnoreCase("Category")){
            context = new Context(new SearchByCategory());
            searchResults = context.findBooks(bookRepository,q);
        }else
            if(searchOptions.equalsIgnoreCase("Author")){
                context = new Context(new SearchByAuthor());
                searchResults = context.findBooks(bookRepository,q);
            }
            else
                if(searchOptions.equalsIgnoreCase("Title")){
                    context = new Context(new SearchByTitle());
                    searchResults = context.findBooks(bookRepository,                   q);
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

        User user = userRepository.findByEmail(email);
        review.setUser(user);
        book.getReview().add(review);
        bookRepository.save(book);
        reviewRepository.save(review);
        return "Welcome.html";
    }


}
