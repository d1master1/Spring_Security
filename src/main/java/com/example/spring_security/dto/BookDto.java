package com.example.spring_security.dto;

import com.example.spring_security.model.Author;
import com.example.spring_security.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BookDto {

    @NotEmpty
    @Size(max = 30, message = "Не более 30 символов")
    private String title;

    @NotEmpty
    private Author author;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}