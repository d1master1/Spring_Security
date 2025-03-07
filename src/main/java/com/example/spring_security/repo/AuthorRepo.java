package com.example.spring_security.repo;

import com.example.spring_security.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {

    void deleteById(Long id);

}