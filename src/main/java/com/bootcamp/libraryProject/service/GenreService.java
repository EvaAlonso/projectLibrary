package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Genre;
import com.bootcamp.libraryProject.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository GenreRepository;

    public GenreService(GenreRepository GenreRepository){
        this.GenreRepository = GenreRepository;
    }
    public List<Genre> getAll(){
        return GenreRepository.findAll();
    }
    public Genre addGenre(Genre newGenre){
        return GenreRepository.save(newGenre);
    }
    public void deleteGenre(int id){
        GenreRepository.deleteById(id);
    }
    public Optional<Genre> findGenre(int id){
        return GenreRepository.findById(id);
    }
    public Genre updateGenre(int id, Genre updateGenre){
        Optional<Genre> foundGenre = GenreRepository.findById(id);

        if(foundGenre.isPresent()){
            Genre existingGenre = foundGenre.get();

            existingGenre.setTitle(updateGenre.getTitle());

            return GenreRepository.save(existingGenre);
        }
        throw new RuntimeException("Genre not found with id: " + id);
    }

}
