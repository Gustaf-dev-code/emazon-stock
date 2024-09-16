package com.example.emazon.domain.exception;


public class MaxCharAllowedException extends RuntimeException{
    public MaxCharAllowedException(String message) {
        super(message);
    }
}
