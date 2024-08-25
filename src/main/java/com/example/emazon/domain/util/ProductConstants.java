package com.example.emazon.domain.util;

public final class ProductConstants {

    private ProductConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        CODE,
        DESCRIPTION,
        URL_IMAGE,

        PRICE,
        DATA_CREATED,
        DATE_UPDATED,
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_CODE_NULL_MESSAGE = "Field 'code' cannot be null";
    public static final String FIELD_PRICE_NULL_MESSAGE = "Field 'price' cannot be null";
    public static final String FIELD_QUANTITY_NULL_MESSAGE = "Field 'quantity' cannot be null";
    public static final String FIELD_SUPPLIER_NULL_MESSAGE = "Field 'supplier' cannot be null";
    public static final String FIELD_CONTACT_NUMBER_NULL_MESSAGE = "Field 'contact number' cannot be null";
}
