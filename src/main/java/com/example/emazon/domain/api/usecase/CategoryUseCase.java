package com.example.emazon.domain.api.usecase;

import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;
import com.example.emazon.domain.spi.ICategoryPersistentPort;
import com.example.emazon.domain.util.PaginationValidator;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistentPort categoryPersistentPort;
    private final PaginationValidator paginationValidator;

    public CategoryUseCase(ICategoryPersistentPort categoryPersistentPort, PaginationValidator paginationValidator) {
        this.categoryPersistentPort = categoryPersistentPort;
        this.paginationValidator = paginationValidator;
    }
    @Override
    public Category save(Category category) {
         return categoryPersistentPort.save(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryPersistentPort.getCategoryByName(name);
    }


    @Override
    public PaginatedResponse<Category> getAllCategoriesPaginated(PaginationRequest paginationRequest) {
        paginationValidator.validatePaginationRequest(paginationRequest);

        return categoryPersistentPort.getAllCategoriesPaginated(paginationRequest);
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryPersistentPort.getAllCategories();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryPersistentPort.getCategoryById(id);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryPersistentPort.updateCategory(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryPersistentPort.deleteById(id);
    }



}
