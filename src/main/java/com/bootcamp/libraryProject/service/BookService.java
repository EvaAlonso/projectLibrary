package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.exception.*;
import com.bootcamp.libraryProject.model.Author;
import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.model.Genre;
import com.bootcamp.libraryProject.repository.AuthorRepository;
import com.bootcamp.libraryProject.repository.BookRepository;
import com.bootcamp.libraryProject.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }


    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    public Book addBook(Book newBook){
        int genreId = newBook.getGenre().getId();
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        if(optionalGenre.isPresent()){
            Genre genre = optionalGenre.get();
            newBook.setGenre(genre);
            return bookRepository.save(newBook);
        }
        throw new GenreNotFoundException(newBook.getGenre().getTitle());
    }
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }
    public Optional<Book> findBook(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        if (foundBook.isPresent()){
            return bookRepository.findById(id);
        } throw new ObjectNotFoundException("Book", id);
    }
    public Book updatedBook(int id, Book updatedBook){

        Optional<Book> foundBook = bookRepository.findById(id);

        if(foundBook.isPresent()){
            Book existingBook = foundBook.get();

            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setState(updatedBook.getState());

            return bookRepository.save(existingBook);
        }
        throw new ObjectNotFoundException("Book", id);
    }
    public Optional<Book> findBookByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(book.isEmpty()){
            throw new IsbnNotFoundException(isbn);
        }
        return book;
    }
    public Optional<Book> findBookByTitle(String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        if (book.isEmpty()){
            throw new TitleNotFoundException(title);
        }
        return book;
    }

    public List<Book> findBookByGenre(String title) {
        Genre genre = genreRepository.findByTitle(title);
        if (genre != null){
            return bookRepository.findByGenre(genre);
        }
        throw new GenreNotFoundException(title);
    }
    public List<Book> findBookByAuthors(String name) {
        Author author = authorRepository.findByName(name);
        if(author != null){
            return bookRepository.findByAuthors(author);
        }
        throw new AuthorNotFoundException(name);
    }
}
