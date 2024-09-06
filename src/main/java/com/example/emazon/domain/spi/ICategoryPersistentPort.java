package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;

import java.util.List;

public interface ICategoryPersistentPort {
    Category save(Category category);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    PaginatedResponse<Category> getAllCategoriesPaginated(PaginationRequest paginationRequest);
    Category getCategoryById(Integer id);
    Category updateCategory(Category category);
    void deleteById(Integer id);

}
