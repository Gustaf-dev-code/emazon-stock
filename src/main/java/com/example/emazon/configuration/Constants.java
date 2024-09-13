package com.example.emazon.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }


    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "The %s with the %s: %s does not exist";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The category already exists";

    public static final String ENTITY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The %s with the %s: %s already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s can not be empty";
    public static final String NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE = "Field %s can not receive negative values";
    public static final String MAX_CHAR_ALLOWED_EXCEPTION_MESSAGE = "%s";

    public static final String INVALID_SORT_DIRECTION_EXCEPTION_MESSAGE = "Invalid sort direction. Must be 'ASC' or 'DESC'.";

}
