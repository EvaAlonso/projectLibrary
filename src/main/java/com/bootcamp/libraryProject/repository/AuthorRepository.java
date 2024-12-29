package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByName(String name);
}
