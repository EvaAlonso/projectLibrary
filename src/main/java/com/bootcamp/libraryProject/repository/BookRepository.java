package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Integer>{
}
