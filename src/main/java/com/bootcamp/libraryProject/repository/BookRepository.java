package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Author;
import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository <Book, Integer>{
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    List<Book> findByAuthors(Author author);
    List<Book> findByGenre(Genre genre);

}
