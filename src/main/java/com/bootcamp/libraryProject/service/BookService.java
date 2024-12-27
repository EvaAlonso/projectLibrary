package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Author;
import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.model.Genre;
import com.bootcamp.libraryProject.repository.BookRepository;
import com.bootcamp.libraryProject.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository BookRepository;
    private final GenreRepository GenreRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository) {
        BookRepository = bookRepository;
        GenreRepository = genreRepository;
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
        //Buscar producto por id
        Optional<Book> foundBook = BookRepository.findById(id);

        if(foundBook.isPresent()){
            Book existingBook = foundBook.get();

            //Actualizar los campos
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setIsbn(updatedBook.getIsbn());

            //guarda el libro
            return BookRepository.save(existingBook);
        }
        //Enviar mensaje al usuario
        throw new RuntimeException("Book not found with id: " + id);
    }
    public Optional<Book> findBookByIsbn(String isbn) { return BookRepository.findByIsbn(isbn); }
    public Optional<Book> findBookByTitle(String title) { return BookRepository.findByTitle(title);}
    public List<Book> findBookByGenre(String genreName) {
        Genre genre = GenreRepository.findByTitle(genreName);
        if (genre != null){
            return BookRepository.findByGenre(genre);
        }
        throw new RuntimeException("Genre not found");
    }
    public List<Book> getBooksByAuthor(Author author) {
        return BookRepository.findByAuthors(author);
    }
}
