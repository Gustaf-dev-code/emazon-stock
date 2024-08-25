package com.example.emazon.domain.model;

import com.example.emazon.domain.exception.MaxCharLenght;
import com.example.emazon.domain.util.CategoryConstants;
import com.example.emazon.domain.exception.EmptyFieldException;

import static java.util.Objects.requireNonNull;

public class Category {
    private final Integer id;
    private final String name;
    private final String description;

    public Category(Integer id, String name, String description) {

        if(name.trim().isEmpty()){
            throw new EmptyFieldException(CategoryConstants.Field.NAME.toString());
        }
        if(name.length() > 50){
            throw new MaxCharLenght(CategoryConstants.Field.NAME.toString());
        }
        if(description.trim().isEmpty()){
            throw new EmptyFieldException(CategoryConstants.Field.DESCRIPTION.toString());
        }
        if(description.length() > 90){
            throw new MaxCharLenght(CategoryConstants.Field.DESCRIPTION.toString());
        }

        this.id = id;
        this.name = requireNonNull(name, CategoryConstants.FIELD_NAME_NULL_MESSAGE);
        this.description =requireNonNull(description, CategoryConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
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
