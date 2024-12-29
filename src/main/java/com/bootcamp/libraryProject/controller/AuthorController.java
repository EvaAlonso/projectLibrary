package com.bootcamp.libraryProject.controller;

import com.bootcamp.libraryProject.model.Author;
import com.bootcamp.libraryProject.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/authors")
@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.getAll();
    }
    @PostMapping
    public void createAuthor(@RequestBody Author newAuthor){
        authorService.addAuthor(newAuthor);
    }
    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable int id){
        authorService.deleteAuthor(id);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable int id){
        Optional<Author> foundAuthor = authorService.findAuthor(id);

        if(foundAuthor.isPresent()){
            return new ResponseEntity<>(foundAuthor.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author updateAuthor){
        try {
            Author author = authorService.updatedAuthor(id, updateAuthor);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
