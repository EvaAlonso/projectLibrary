package com.bootcamp.libraryProject.controller;

import com.bootcamp.libraryProject.exception.ObjectNotFoundException;
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
    public ResponseEntity<Book> createBook(@RequestBody Book newBook){
        try {
            Book createdBook =  bookService.addBook(newBook);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        throw new ObjectNotFoundException("Book", id);
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
    @RequestMapping("/books/isbn/{isbn}")
    public ResponseEntity<Book> findBookWithIsbn(@PathVariable String isbn) {

        Optional<Book> foundBookWithIsbn = bookService.findBookByIsbn(isbn);

        if(foundBookWithIsbn.isPresent()) { return new ResponseEntity<>(foundBookWithIsbn.get(), HttpStatus.FOUND); } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping("/books/title/{title}")
    public ResponseEntity<Book> findBookWithTitle(@PathVariable String title) {

        Optional<Book> foundBookWithTitle = bookService.findBookByTitle(title);

        if(foundBookWithTitle.isPresent()) { return new ResponseEntity<>(foundBookWithTitle.get(), HttpStatus.FOUND); } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //@RequestMapping("/books/title/{genre}")
   // public ResponseEntity<Book> findBookWithGenre(@PathVariable String genre) {

   //     Optional<Book> foundBookWithGenre = bookService.findBookByGenre(genre);

   //     if(foundBookWithGenre.isPresent()) { return new ResponseEntity<>(foundBookWithGenre.get(), HttpStatus.FOUND); } else
    //        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //}
}
