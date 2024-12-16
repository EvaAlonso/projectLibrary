package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Author;
import com.bootcamp.libraryProject.repository.IAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final IAuthorRepository IAuthorRepository;

    public AuthorService(IAuthorRepository IAuthorRepository){
        this.IAuthorRepository = IAuthorRepository;
    }
    public List<Author> getAll(){
        return  IAuthorRepository.findAll();
    }
    public Author addAuthor(Author newAuthor){
        return IAuthorRepository.save(newAuthor);
    }
    public void deleteAuthor(int id){
        IAuthorRepository.deleteById(id);
    }
    public Optional<Author> findAuthor(int id){
        return IAuthorRepository.findById(id);
    }

    public Author updatedAuthor(int id, Author updateAuthor){
        Optional<Author> foundAuthor = IAuthorRepository.findById(id);

        if(foundAuthor.isPresent()){
            Author existingAuthor = foundAuthor.get();
            existingAuthor.setName(updateAuthor.getName());
            existingAuthor.setBiography(updateAuthor.getBiography());

            return IAuthorRepository.save(existingAuthor);
        }
        throw new RuntimeException("Author not found with id: " + id);
    }
}
