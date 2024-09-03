package com.example.emazon.domain.exception;

public class NegativeNotAllowedException extends RuntimeException{
    public NegativeNotAllowedException(String message) {
        super(message);
    }
}
