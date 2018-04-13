package com.example.bookstore2.demo.Repository;

import com.example.bookstore2.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User findById(int id);
}
