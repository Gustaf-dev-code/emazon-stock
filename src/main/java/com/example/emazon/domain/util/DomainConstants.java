package com.example.emazon.domain.util;

public final class DomainConstants {


    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final int FIELD_NAME_MIN_SIZE = 2;

    public static final int FIELD_NAME_MAX_SIZE = 50;

    public static final String FIELD_NAME_SIZE_MESSAGE = "Field 'name' must be between " + FIELD_NAME_MIN_SIZE + " and " + FIELD_NAME_MAX_SIZE +" characters";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final int FIELD_DESCRIPTION_MIN_SIZE = 10;

    public static final int FIELD_DESCRIPTION_CATEGORY_MAX_SIZE = 90;
    public static final int FIELD_DESCRIPTION_BRAND_MAX_SIZE = 120;

    public static final String FIELD_DESCRIPTION_CATEGORY_SIZE_MESSAGE = "'description' must be between " + FIELD_DESCRIPTION_MIN_SIZE + " and " + FIELD_DESCRIPTION_CATEGORY_MAX_SIZE +" characters";
    public static final String FIELD_DESCRIPTION_BRAND_SIZE_MESSAGE = "'description' must be between " + FIELD_DESCRIPTION_MIN_SIZE + " and " + FIELD_DESCRIPTION_BRAND_MAX_SIZE +" characters";
}
