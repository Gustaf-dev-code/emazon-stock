package com.example.emazon.domain.model;

import com.example.emazon.domain.exception.EmptyFieldException;
import com.example.emazon.domain.exception.MaxCharAllowedException;
import com.example.emazon.domain.util.DomainConstants;

public class Brand {
    private final Integer id;
    private final String name;
    private final String description;

    public Brand(Integer id, String name, String description) {
        if(name.trim().isEmpty()){
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if(name.length() < DomainConstants.FIELD_NAME_MIN_SIZE || name.length() > DomainConstants.FIELD_NAME_MAX_SIZE){
            throw new MaxCharAllowedException(DomainConstants.FIELD_NAME_SIZE_MESSAGE);
        }
        if(description.trim().isEmpty()){
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
        if(description.length() < DomainConstants.FIELD_DESCRIPTION_MIN_SIZE || description.length() > DomainConstants.FIELD_DESCRIPTION_BRAND_MAX_SIZE){
            throw new MaxCharAllowedException(DomainConstants.FIELD_DESCRIPTION_BRAND_SIZE_MESSAGE);
        }
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
