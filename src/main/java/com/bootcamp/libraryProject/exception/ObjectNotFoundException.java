package com.bootcamp.libraryProject.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String objectName, int id){

        super("Could not find " + objectName + " with id: " + id);
    }
}
