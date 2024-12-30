package com.bootcamp.libraryProject.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(String genreTitle) {
        super("Could not find books with genre: " + genreTitle);
    }
}
