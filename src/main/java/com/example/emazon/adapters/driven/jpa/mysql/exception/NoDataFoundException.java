package com.example.emazon.adapters.driven.jpa.mysql.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(String message) {
        super(message);
    }
}
