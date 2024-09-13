package com.example.emazon.adapters.driven.jpa.mysql.exception;

import com.example.emazon.configuration.Constants;
import lombok.Getter;

@Getter
public class ElementNotFoundException extends RuntimeException{

    private final String entity;
    private final String field;
    private final String value;
    public ElementNotFoundException(String entity, String field, String value) {
        super(String.format(Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE, entity, field, value));
        this.entity = entity;
        this.field = field;
        this.value = value;
    }

}
