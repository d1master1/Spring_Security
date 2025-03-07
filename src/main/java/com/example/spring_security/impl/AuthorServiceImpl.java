package com.example.spring_security.impl;

import com.example.spring_security.dto.AuthorDto;
import com.example.spring_security.model.Author;
import com.example.spring_security.repo.AuthorRepo;
import com.example.spring_security.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public void saveAuthor(AuthorDto authorDto) {
        Author newAuthor = new Author();
        newAuthor.setName(authorDto.getName());
        newAuthor.setSurname(authorDto.getSurname());
        authorRepo.save(newAuthor);
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepo.findById(id).orElseThrow(null);
    }

    @Override
    public void deleteAuthor(Long id) {
        this.authorRepo.deleteById(id);
    }

    @Override
    public void editAuthor(Author author) {
        Author editedAuthor = authorRepo.findById(author.getId()).orElseThrow(null);
        if (editedAuthor == null) {
            throw new IllegalArgumentException("Автор не найден");
        }
        editedAuthor.setName(author.getName());
        editedAuthor.setSurname(author.getSurname());
        authorRepo.save(editedAuthor);
    }
    @Override
    public boolean hasBooks(Long id) {
        return bookRepo.existsByAuthorId(id);
    }
}