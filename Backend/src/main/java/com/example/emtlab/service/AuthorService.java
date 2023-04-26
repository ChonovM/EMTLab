package com.example.emtlab.service;

import com.example.emtlab.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    Author addAuthor(Author author);
    Author editAuthor(Long id, Author author);
    void deleteAuthor(Long id);
}
