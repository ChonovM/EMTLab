package com.example.emtlab.service;


import com.example.emtlab.exceptions.NotEnoughCopiesException;
import com.example.emtlab.model.Author;
import com.example.emtlab.model.Book;
import com.example.emtlab.model.dto.BookDto;
import com.example.emtlab.model.enumerations.Category;
import com.example.emtlab.repositories.AuthorRepository;
import com.example.emtlab.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> addBook(String name, Category category, Long author, Integer availableCopies) {
        Author author1 = authorRepository.findById(author).orElseThrow();
        Book book = new Book(name, category, author1, availableCopies);
        bookRepository.save(book);
        System.out.println(author1);
        return Optional.of(book);
    }

    @Override
    public  Optional<Book> addBook(BookDto bookDto){
        return addBook(bookDto.getName(), bookDto.getCategory(), bookDto.getAuthor(), bookDto.getAvailableCopies());
    }

    @Override
    public Optional<Book> editBook(Long id, String name, Category category, Long author, Integer availableCopies) {
        Book existingBook = bookRepository.findById(id).orElseThrow();
        Author author1 = authorRepository.findById(author).orElseThrow();
        existingBook.setName(name);
        existingBook.setAuthor(author1);
        existingBook.setCategory(category);
        existingBook.setAvailableCopies(availableCopies);
        bookRepository.save(existingBook);
        return Optional.of(existingBook);
    }

    @Override
    public Optional<Book> editBook(Long id, BookDto bookDto){
        return editBook(id, bookDto.getName(), bookDto.getCategory(), bookDto.getAuthor(), bookDto.getAvailableCopies());
    }

    @Override
    public void deleteBook(Long id) {
        Book bookToDelete = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(bookToDelete);
    }

    @Override
    public void rentBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
        } else {
            throw new NotEnoughCopiesException("There are no available copies of book with id: " + id);
        }
    }

    @Override
    public void returnBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }
}

