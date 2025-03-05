package com.example.spring_security.controller;

import com.example.spring_security.dto.UserDto;
import com.example.spring_security.repo.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDto user() {
        return new UserDto();
    }

    @GetMapping("/register")
    String register() {
        return "register";
    }

    @PostMapping("/save")
    String register(@ModelAttribute("user") UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }
}