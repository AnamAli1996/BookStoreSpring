package com.example.bookstore2.demo.Repository;

import com.example.bookstore2.demo.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
