package com.example.emtlab.repositories;

import com.example.emtlab.model.Author;
import com.example.emtlab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}