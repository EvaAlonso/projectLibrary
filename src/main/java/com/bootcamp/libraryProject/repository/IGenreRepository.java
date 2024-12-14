package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IGenreRepository extends JpaRepository<Genre, Integer> {

}
