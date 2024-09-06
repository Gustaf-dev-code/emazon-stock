package com.example.emazon.domain.util;

public class PaginationConstants {

    public static final int DEFAULT_PAGE = 0;

    public static final int DEFAULT_SIZE = 10;

    public static final String DEFAULT_SORT_BY = "name";

    public static final String DEFAULT_SORT_DIRECTION = "ASC";

    private PaginationConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String INVALID_NUMBER_PAGE_EXCEPTION_MESSAGE = "Page number cannot be negative";

    public static final String INVALID_SIZE_PAGE_EXCEPTION_MESSAGE = "Page size cannot be negative";

}
