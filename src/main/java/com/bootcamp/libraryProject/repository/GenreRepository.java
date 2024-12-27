package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByTitle(String title);
}
