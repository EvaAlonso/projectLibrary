package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IBookRepository extends JpaRepository <Book, Integer>{
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    Optional<Book> findByGenre(String genre);
}
