package com.example.emazon.domain.exception;

import com.example.emazon.configuration.Constants;
import lombok.Getter;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {

    private final String entityName;
    private final String fieldName;
    private final String value;
    public EntityAlreadyExistsException(String entityName, String fieldName, String value) {
        super(String.format(Constants.ENTITY_ALREADY_EXISTS_EXCEPTION_MESSAGE, entityName, fieldName, value));
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.value = value;
    }

}
