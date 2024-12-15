package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.model.Genre;
import com.bootcamp.libraryProject.repository.IBookRepository;
import com.bootcamp.libraryProject.repository.IGenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final IBookRepository IBookRepository;
    private final IGenreRepository IGenreRepository ;

    public BookService(com.bootcamp.libraryProject.repository.IBookRepository iBookRepository, com.bootcamp.libraryProject.repository.IGenreRepository iGenreRepository) {
        IBookRepository = iBookRepository;
        IGenreRepository = iGenreRepository;
    }


    public List<Book> getAll(){
        return IBookRepository.findAll();
    }
    public Book addBook(Book newBook){
        int genreId = newBook.getGenre().getId();
        Optional<Genre> optionalGenre = IGenreRepository.findById(genreId);
        if(optionalGenre.isPresent()){
            Genre genre = optionalGenre.get();
            newBook.setGenre(genre);
            return IBookRepository.save(newBook);
        }
        throw new RuntimeException("Genre not found");
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
    //public Optional<Book> findBookByGenre(String genre) { return IBookRepository.findByGenre(genre);}
}
