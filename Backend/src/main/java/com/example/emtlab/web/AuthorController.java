package com.example.emtlab.web;

import com.example.emtlab.model.Author;
import com.example.emtlab.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*",  methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST})
@RequestMapping({"/api/authors"})
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}
