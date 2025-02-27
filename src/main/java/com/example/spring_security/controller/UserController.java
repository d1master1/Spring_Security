package com.example.spring_security.controller;

import ch.qos.logback.core.model.Model;
import com.example.spring_security.model.User;
import com.example.spring_security.model.UserDto;
import com.example.spring_security.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    private final UserService userService;
    private final BookService bookService;

    public UserController(UserService userServiceImpl, BookService bookService) {
        this.userService = userServiceImpl;
        this.bookService = bookService;
    }

    @ModelAttribute("user")
    public UserDto user() { return new UserDto(); }

    @GetMapping("/registration")
    String registration() { return "registration"; }

    @PostMapping("/save")
    String save(@ModelAttribute("user") UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }

    @GetMapping("/profile")

    @PreAuthorize("isAuthenticated()")
    String profile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.findUser(user.getUsername()));
        model.addAttribute("books", bookService.findByUser(userService.findUser(user.getUsername())));
        model.addAttribute("title", "Электронная библиотека Профиль пользователя");
        return "profile";
    }
}