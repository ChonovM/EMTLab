package com.example.emtlab.service;

import com.example.emtlab.exceptions.AuthorNotFoundException;
import com.example.emtlab.model.Author;
import com.example.emtlab.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        System.out.println(id);
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author with id: " + id + "not found."));
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author editAuthor(Long id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author with id: " + id + "not found."));
        existingAuthor.setName(author.getName());
        existingAuthor.setSurname(author.getSurname());
        existingAuthor.setCountry(author.getCountry());
        return authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not with id: " + id + "not found"));
        authorRepository.delete(author);
    }

}