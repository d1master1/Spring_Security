package com.example.spring_security.repo;

import com.example.spring_security.model.Author;
import com.example.spring_security.model.Book;
import com.example.spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findByUser(User user);

    List<Book> findByAuthor(Author author);

    void deleteById(Long id);

    boolean existsByAuthorId(Long authorId);

}