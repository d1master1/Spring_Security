package com.example.spring_security.service;

import com.example.spring_security.dto.BookDto;
import com.example.spring_security.model.Book;
import com.example.spring_security.model.User;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    List<Book> findByUser(User user);

    void saveBook(BookDto bookDto);

    Book findBookById(Long id);

    void deleteBook(Long id);

    void editBook(Book book);

}