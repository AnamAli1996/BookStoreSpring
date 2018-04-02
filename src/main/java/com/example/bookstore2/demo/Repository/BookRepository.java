package com.example.bookstore2.demo.Repository;

import com.example.bookstore2.demo.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
