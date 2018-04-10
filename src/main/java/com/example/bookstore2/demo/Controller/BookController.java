package com.example.bookstore2.demo.Controller;

import com.example.bookstore2.demo.Entity.Book;
import com.example.bookstore2.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

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
}
