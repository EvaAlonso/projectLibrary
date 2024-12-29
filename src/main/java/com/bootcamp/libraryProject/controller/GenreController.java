package com.bootcamp.libraryProject.controller;

import com.bootcamp.libraryProject.exception.ObjectNotFoundException;
import com.bootcamp.libraryProject.model.Genre;
import com.bootcamp.libraryProject.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/genres")
@RestController
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService){
        this.genreService = genreService;

    }
    @GetMapping
    public List<Genre> getAllGenres(){
        return genreService.getAll();
    }
    @PostMapping
    public void createGenre(@RequestBody Genre newGenre){
        genreService.addGenre(newGenre);
    }
    @DeleteMapping("/{id}")
    public void deleteGenreById(@PathVariable int id){
        genreService.deleteGenre(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Genre> findGenreById(@PathVariable int id){
        Optional<Genre> foundGenre = genreService.findGenre(id);
        if(foundGenre.isPresent()){
            return new ResponseEntity<>(foundGenre.get(), HttpStatus.FOUND);
        }
        throw new ObjectNotFoundException("Genre", id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable int id, @RequestBody Genre updatedGenre){
        try {
            Genre genre = genreService.updateGenre(id, updatedGenre);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
