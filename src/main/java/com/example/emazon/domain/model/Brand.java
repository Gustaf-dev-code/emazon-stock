package com.example.emazon.domain.model;

import com.example.emazon.domain.exception.EmptyFieldException;
import com.example.emazon.domain.exception.MaxCharAllowedException;
import com.example.emazon.domain.util.BrandConstants;

public class Brand {
    private final Integer id;
    private final String name;
    private final String description;

    public Brand(Integer id, String name, String description) {
        if(name.trim().isEmpty()){
            throw new EmptyFieldException(BrandConstants.Field.NAME.toString());
        }
        if(name.length() > 50){
            throw new MaxCharAllowedException(BrandConstants.Field.NAME.toString());
        }
        if(description.trim().isEmpty()){
            throw new EmptyFieldException(BrandConstants.Field.DESCRIPTION.toString());
        }
        if(description.length() > 120){
            throw new MaxCharAllowedException(BrandConstants.Field.DESCRIPTION.toString());
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
