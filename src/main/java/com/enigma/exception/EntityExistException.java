package com.enigma.exception;

public class EntityExistException extends RuntimeException{

    public EntityExistException() {
        super("Data already exist");
    }

    public EntityExistException(String message) {
        super(message);
    }
}
