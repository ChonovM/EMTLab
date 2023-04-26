package com.example.emtlab.web;

import com.example.emtlab.model.Author;
import com.example.emtlab.model.Book;
import com.example.emtlab.model.dto.BookDto;
import com.example.emtlab.service.AuthorService;
import com.example.emtlab.model.enumerations.Category;
import com.example.emtlab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*",  methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping({"/api/books", "/"})
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
            return bookService.addBook(bookDto)
                    .map(book -> ResponseEntity.ok().body(book))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.editBook(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/rent/{id}")
    public void rentBook(@PathVariable Long id) {
        bookService.rentBook(id);
    }
}
