package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    public Book addBook(Book newBook){
        return bookRepository.save(newBook);
    }
}
