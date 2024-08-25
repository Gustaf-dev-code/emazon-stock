package com.example.emazon.domain.exception;

public class NegativeNotAllowed extends RuntimeException{
    public NegativeNotAllowed(String message) {
        super(message);
    }
}
