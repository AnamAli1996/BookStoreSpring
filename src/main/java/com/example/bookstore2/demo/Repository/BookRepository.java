package com.example.bookstore2.demo.Repository;

import com.example.bookstore2.demo.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

   List<Book> findByTitleOrAuthor(String search, String author);
   List<Book> findByCategory(String q);
   Book findByTitle(String title);
   Book findById(int id);
}
