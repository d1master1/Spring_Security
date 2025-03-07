package com.example.spring_security.controller;

import com.example.spring_security.dto.BookDto;
import com.example.spring_security.model.User;
import com.example.spring_security.repo.UserService;
import com.example.spring_security.service.AuthorService;
import com.example.spring_security.service.BookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BooksController {

    private final BookService bookService;
    private final UserService userService;
    private final AuthorService authorService;

    public BooksController(BookService bookService, UserService userService, AuthorService authorService) {
        this.bookService = bookService;
        this.userService = userService;
        this.authorService = authorService;
    }

    @GetMapping("/addBook")
    String addBook(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("user", userService.findUser(user.getUsername()));
        } else {
            model.addAttribute("user", null);
        }
        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("book", new BookDto());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "addBook";
    }

    @PostMapping("/saveBook")
    String saveBook(@ModelAttribute("book") BookDto bookDto, @AuthenticationPrincipal User user, BindingResult bindingResult) {

        bookDto.setUser(userService.findUser(user.getUsername()));

        if (bindingResult.hasErrors()) {
            return "addBook";
        }

        bookService.saveBook(bookDto);

        return "redirect:/profile";
    }

    @GetMapping("/booksList")
    String booksList(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.findUser(user.getUsername()));
        model.addAttribute("books", bookService.findAllBooks());
        return "booksList";
    }
}