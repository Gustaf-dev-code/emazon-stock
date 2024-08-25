package com.example.emazon.domain.model;


import com.example.emazon.domain.exception.NegativeNotAllowed;
import com.example.emazon.domain.util.ProductConstants;
import com.example.emazon.domain.exception.EmptyFieldException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private Integer id;
    private String name;
    private String code;
    private String description;
    private String urlImage;
    private BigDecimal price;;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private Integer userId;
    private Integer categoryId;


    public Product(Integer id, String name, String code, String description, String urlImage, BigDecimal price, LocalDateTime dateCreated, LocalDateTime dateUpdated, Integer userId, Integer categoryId) {
        if(name.trim().isEmpty()){
            throw new EmptyFieldException(ProductConstants.Field.NAME.toString());
        }
        if(code.trim().isEmpty()){
            throw new EmptyFieldException(ProductConstants.Field.CODE.toString());
        }
        if(price.compareTo(BigDecimal.ZERO) < 0){
            throw new NegativeNotAllowed(ProductConstants.Field.PRICE.toString());
        }


        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.urlImage = urlImage;
        this.price = price;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
