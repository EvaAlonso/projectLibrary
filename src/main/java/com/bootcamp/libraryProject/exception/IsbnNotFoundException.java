package com.bootcamp.libraryProject.exception;

public class IsbnNotFoundException extends RuntimeException {
    public IsbnNotFoundException(String isbn) {
        super("Could not find book with ISBN: " + isbn);
    }
}
