package com.example.emazon.domain.exception;

public class RepeatingCategoryNameNotAllowed extends RuntimeException{
    public RepeatingCategoryNameNotAllowed(String message) {
        super(message);
    }
}
