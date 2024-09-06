package com.example.emazon.domain.api.usecase;

import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.exception.EmptyFieldException;
import com.example.emazon.domain.exception.InvalidNumberPageException;
import com.example.emazon.domain.exception.InvalidSizePageException;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;
import com.example.emazon.domain.model.SortDirection;
import com.example.emazon.domain.spi.ICategoryPersistentPort;
import com.example.emazon.domain.util.PaginationConstants;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistentPort categoryPersistentPort;

    public CategoryUseCase(ICategoryPersistentPort categoryPersistentPort) {
        this.categoryPersistentPort = categoryPersistentPort;
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
        validatePaginationRequest(paginationRequest);

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


    private void validatePaginationRequest(PaginationRequest paginationRequest) {
        if(paginationRequest == null) {
            paginationRequest = new PaginationRequest(0, 10, "name", SortDirection.ASC);
        }
        if(paginationRequest.getPage() < 0) {
            throw new InvalidNumberPageException(PaginationConstants.INVALID_NUMBER_PAGE_EXCEPTION_MESSAGE);
        }
        if(paginationRequest.getSize() < 0) {
            throw new InvalidSizePageException(PaginationConstants.INVALID_SIZE_PAGE_EXCEPTION_MESSAGE);
        }
        if(paginationRequest.getPage() == 0 || paginationRequest.getSize() == 0) {
            throw new EmptyFieldException("Pagination request cannot have null fields");
        }
        if(paginationRequest.getSortDirection() == null) {
            throw new EmptyFieldException("Sort direction cannot be null");
        }
        if(paginationRequest.getSortBy() == null) {
            throw new EmptyFieldException("Sort by cannot be null");
        }
    }
}
