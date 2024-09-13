package com.example.emazon.domain.exception;

import com.example.emazon.configuration.Constants;


public class MaxCharAllowedException extends RuntimeException{
    public MaxCharAllowedException(String message) {
        super(message);
    }
}
