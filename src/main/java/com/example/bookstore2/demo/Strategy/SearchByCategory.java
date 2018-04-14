package com.example.bookstore2.demo.Strategy;

import com.example.bookstore2.demo.Entity.Book;
import com.example.bookstore2.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchByCategory implements Strategy{


    @Override
    public List<Book> findBooks(BookRepository bookRepository, String search) {
        return bookRepository.findByCategory(search);
    }
}
