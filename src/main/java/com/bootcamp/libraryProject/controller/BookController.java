package com.bootcamp.libraryProject.controller;

import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }
    @PostMapping("/books")
    public void createBook(@RequestBody Book newBook){
        bookService.addBook(newBook);
    }
    @DeleteMapping("/books/{id}")
    public void deleteProductById(@PathVariable int id){
        bookService.deleteBook(id);
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id){
        Optional<Book> foundBook = bookService.findBook(id);
        if(foundBook.isPresent()){
            return new ResponseEntity<>(foundBook.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook){
        try {
            //actualizar los campos del book en el caso de que encuentre
            Book book = bookService.updatedBook(id, updatedBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            //en el caso de que no encuentre devuelve not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
