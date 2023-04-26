package com.example.emtlab.service;

import com.example.emtlab.model.Author;
import com.example.emtlab.model.Book;
import com.example.emtlab.model.dto.BookDto;
import com.example.emtlab.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Optional<Book> addBook(String name, Category category, Long author, Integer availableCopies);

    Optional<Book> addBook(BookDto bookDto);

    Optional<Book> editBook(Long id, BookDto bookDto);

    Optional<Book> editBook(Long id, String name, Category category, Long author, Integer availableCopies);

    void deleteBook(Long id);

    void rentBook(Long id);

    void returnBook(Long id);

}
