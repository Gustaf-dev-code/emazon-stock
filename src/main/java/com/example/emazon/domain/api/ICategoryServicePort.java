package com.example.emazon.domain.api;

import com.example.emazon.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {
    Category save(Category category);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
    Category updateCategory(Category category);
    void deleteById(Integer id);
}
