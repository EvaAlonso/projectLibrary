package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }
    public Optional<Book> findBook(int id){
        return bookRepository.findById(id);
    }
    public Book updatedBook(int id, Book updatedBook){
        //Buscar producto por id
        Optional<Book> foundBook = bookRepository.findById(id);

        if(foundBook.isPresent()){
            Book existingBook = foundBook.get();

            //Actualizar los campos
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setIsbn(updatedBook.getIsbn());

            //guarda el libro
            return bookRepository.save(existingBook);
        }
        //Enviar mensaje al usuario
        throw new RuntimeException("Product not found with id: " + id);
    }
}
