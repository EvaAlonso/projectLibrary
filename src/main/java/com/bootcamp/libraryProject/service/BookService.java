package com.bootcamp.libraryProject.service;

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
    private final BookRepository BookRepository;
    private final GenreRepository GenreRepository;
    private final AuthorRepository AuthorRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        BookRepository = bookRepository;
        GenreRepository = genreRepository;
        AuthorRepository = authorRepository;
    }


    public List<Book> getAll(){
        return BookRepository.findAll();
    }
    public Book addBook(Book newBook){
        int genreId = newBook.getGenre().getId();
        Optional<Genre> optionalGenre = GenreRepository.findById(genreId);
        if(optionalGenre.isPresent()){
            Genre genre = optionalGenre.get();
            newBook.setGenre(genre);
            return BookRepository.save(newBook);
        }
        throw new RuntimeException("Genre not found");
    }
    public void deleteBook(int id){
        BookRepository.deleteById(id);
    }
    public Optional<Book> findBook(int id){
        return BookRepository.findById(id);
    }
    public Book updatedBook(int id, Book updatedBook){

        Optional<Book> foundBook = BookRepository.findById(id);

        if(foundBook.isPresent()){
            Book existingBook = foundBook.get();

            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setState(updatedBook.getState());

            return BookRepository.save(existingBook);
        }
        throw new RuntimeException("Book not found with id: " + id);
    }
    public Optional<Book> findBookByIsbn(String isbn) { return BookRepository.findByIsbn(isbn); }
    public Optional<Book> findBookByTitle(String title) { return BookRepository.findByTitle(title);}
    public List<Book> findBookByGenre(String title) {
        Genre genre = GenreRepository.findByTitle(title);
        if (genre != null){
            return BookRepository.findByGenre(genre);
        }
        throw new RuntimeException("Genre not found");
    }
    public List<Book> findBookByAuthors(String name) {
        Author author = AuthorRepository.findByName(name);
        if(author != null){
            return BookRepository.findByAuthors(author);
        }
        throw new RuntimeException("Author not found");
    }
}
