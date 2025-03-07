package com.example.spring_security.controller;

import com.example.spring_security.model.Author;
import com.example.spring_security.model.User;
import com.example.spring_security.repo.UserService;
import com.example.spring_security.service.AuthorService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("authors/author/{authorId:\\d+}")

public class AuthorController {

    private final AuthorService authorService;
    private final UserService userService;

    public AuthorController(AuthorService authorService, UserService userService) {
        this.authorService = authorService;
        this.userService = userService;
    }

    @ModelAttribute("author")
    public Author author(@PathVariable("authorId") long authorId) {
        return this.authorService.findAuthorById(authorId);
    }

    @GetMapping
    public String getAuthor(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", userService.findUser(user.getUsername()));
        return "/authors/author";
    }

    @PostMapping("/deleteAuthor")
    public String deleteAuthor(@ModelAttribute("author") Author author, RedirectAttributes redirectAttributes) {
        // Проверяем, есть ли у автора книги
        if (authorService.hasBooks(author.getId())) {
            // Если книги есть, добавляем сообщение об ошибке и перенаправляем обратно
            redirectAttributes.addFlashAttribute("errorMessage", "Невозможно удалить автора, так как у него есть связанные книги.");
            return "redirect:/authorsList"; // Перенаправляем на страницу со списком авторов
        }

        // Если книг нет, удаляем автора
        authorService.deleteAuthor(author.getId());
        return "redirect:/authorsList";
    }

    @PostMapping("/editAuthor")
    public String editBook(@ModelAttribute("author") Author author){
        this.authorService.editAuthor(author);
        return "redirect:/authors/author/" + author.getId();
    }

    @GetMapping("/editAuthor")
    public String getEditAuthor(@ModelAttribute("author") Author author, @AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", userService.findUser(user.getUsername()));
        model.addAttribute("author", authorService.findAuthorById(author.getId()));
        return "/editAuthor";
    }
}