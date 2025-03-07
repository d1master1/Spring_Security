package com.example.spring_security.service;

import com.example.spring_security.dto.AuthorDto;
import com.example.spring_security.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    void saveAuthor(AuthorDto authorDto);

    Author findAuthorById(Long id);

    void deleteAuthor(Long id);

    void editAuthor(Author author);

    boolean hasBooks(Long id);

    /*boolean hasBooks(Long id);*/
}