package com.example.emtlab.web;

import com.example.emtlab.model.Book;
import com.example.emtlab.model.enumerations.Category;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*",  methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST})
@RequestMapping({"/api/categories"})
public class CategoryController {
    @GetMapping
    public List<Category> getAllCategories() {
        return Arrays.stream(Category.values()).toList();
    }
}
