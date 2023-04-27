package com.example.emtlab.config;

import com.example.emtlab.model.Author;
import com.example.emtlab.model.Book;
import com.example.emtlab.model.Country;
import com.example.emtlab.model.enumerations.Category;
import com.example.emtlab.service.AuthorService;
import com.example.emtlab.service.BookService;
import com.example.emtlab.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    public static final String ADMIN = "admin";

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService productService, AuthorService authorService, CountryService countryService) {
        this.bookService = productService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData() {
//        User admin = this.userService.create(ADMIN, ADMIN, Role.ROLE_ADMIN);

//        for (int i = 1; i < 4; i++) {
//            this.categoryService.create("Category " + i);
//        }

        Country c1 = this.countryService.addCountry(new Country("United States", "America"));
        Country c2 = this.countryService.addCountry(new Country("Germany", "Europe"));
        Country c3 = this.countryService.addCountry(new Country("Japan", "Asia"));
        Country c4 = this.countryService.addCountry(new Country("Congo", "Africa"));

        Author a1 = this.authorService.addAuthor(new Author("Miguel", "Hernandez", c1));
        Author a2 = this.authorService.addAuthor(new Author("Vladimir", "Stojanovski", c2));
        Author a3 = this.authorService.addAuthor(new Author("Hikaru", "Nakamura", c3));

        bookService.addBook("Dante's Inferno", Category.CLASSICS, a1.getId(), 132);
        bookService.addBook("Mein Kampf", Category.NOVEL, a3.getId(), 1123);
        bookService.addBook("Da Vinci Code", Category.THRILER, a2.getId(), 531);
        bookService.addBook("Fifty Shades of Grey", Category.DRAMA, a1.getId(), 435);
        bookService.addBook("Twilight", Category.HISTORY, a2.getId(), 631);
        bookService.addBook("Hannibal", Category.FANTASY, a3.getId(), 2);
        bookService.addBook("Lord of The Rings", Category.BIOGRAPHY, a3.getId(), 5361);

    }
}