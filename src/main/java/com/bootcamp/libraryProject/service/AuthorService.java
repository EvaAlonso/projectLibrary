package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Author;
import com.bootcamp.libraryProject.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository AuthorRepository;

    public AuthorService(AuthorRepository AuthorRepository){
        this.AuthorRepository = AuthorRepository;
    }
    public List<Author> getAll(){
        return  AuthorRepository.findAll();
    }
    public Author addAuthor(Author newAuthor){
        return AuthorRepository.save(newAuthor);
    }
    public void deleteAuthor(int id){
        AuthorRepository.deleteById(id);
    }
    public Optional<Author> findAuthor(int id){
        return AuthorRepository.findById(id);
    }

    public Author updatedAuthor(int id, Author updateAuthor){
        Optional<Author> foundAuthor = AuthorRepository.findById(id);

        if(foundAuthor.isPresent()){
            Author existingAuthor = foundAuthor.get();
            existingAuthor.setName(updateAuthor.getName());
            existingAuthor.setBiography(updateAuthor.getBiography());

            return AuthorRepository.save(existingAuthor);
        }
        throw new RuntimeException("Author not found with id: " + id);
    }
}
