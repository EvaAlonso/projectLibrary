package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final IBookRepository IBookRepository;

    public BookService(IBookRepository IBookRepository) {
        this.IBookRepository = IBookRepository;
    }
    public List<Book> getAll(){
        return IBookRepository.findAll();
    }
    public Book addBook(Book newBook){
        return IBookRepository.save(newBook);
    }
    public void deleteBook(int id){
        IBookRepository.deleteById(id);
    }
    public Optional<Book> findBook(int id){
        return IBookRepository.findById(id);
    }
    public Book updatedBook(int id, Book updatedBook){
        //Buscar producto por id
        Optional<Book> foundBook = IBookRepository.findById(id);

        if(foundBook.isPresent()){
            Book existingBook = foundBook.get();

            //Actualizar los campos
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setIsbn(updatedBook.getIsbn());

            //guarda el libro
            return IBookRepository.save(existingBook);
        }
        //Enviar mensaje al usuario
        throw new RuntimeException("Book not found with id: " + id);
    }
    public Optional<Book> findBookByIsbn(String isbn) { return IBookRepository.findByIsbn(isbn); }
    public Optional<Book> findBookByTitle(String title) { return IBookRepository.findByTitle(title);}
    public Optional<Book> findBookByGenre(String genre) { return IBookRepository.findByGenre(genre);}
}
