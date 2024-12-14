package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Genre;
import com.bootcamp.libraryProject.repository.IGenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final IGenreRepository IGenreRepository;

    public GenreService(IGenreRepository IGenreRepository){
        this.IGenreRepository = IGenreRepository;
    }
    public List<Genre> getAll(){
        return IGenreRepository.findAll();
    }
    public Genre addGenre(Genre newGenre){
        return IGenreRepository.save(newGenre);
    }
    public void deleteGenre(int id){
        IGenreRepository.deleteById(id);
    }
    public Optional<Genre> findGenre(int id){
        return IGenreRepository.findById(id);
    }
    public Genre updateGenre(int id, Genre updateGenre){
        Optional<Genre> foundGenre = IGenreRepository.findById(id);

        if(foundGenre.isPresent()){
            Genre existingGenre = foundGenre.get();

            existingGenre.setTitle(updateGenre.getTitle());
            existingGenre.setDescription(updateGenre.getDescription());

            return IGenreRepository.save(existingGenre);
        }
        throw new RuntimeException("Genre not found with id: " + id);
    }

}
