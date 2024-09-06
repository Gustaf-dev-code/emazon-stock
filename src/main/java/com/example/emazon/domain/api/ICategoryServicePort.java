package com.example.emazon.domain.api;

import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;

import java.util.List;

public interface ICategoryServicePort {
    Category save(Category category);
    Category getCategoryByName(String name);
    PaginatedResponse<Category> getAllCategoriesPaginated(PaginationRequest paginationRequest);
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
    Category updateCategory(Category category);
    void deleteById(Integer id);
}
