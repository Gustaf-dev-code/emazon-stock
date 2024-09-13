package com.example.emazon.adapters.driven.jpa.mysql.adapter;


import com.example.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.example.emazon.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.example.emazon.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.emazon.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.example.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.example.emazon.configuration.Constants;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;
import com.example.emazon.domain.model.SortDirection;
import com.example.emazon.domain.spi.ICategoryPersistentPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CategoryAdapter implements ICategoryPersistentPort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private static final String ENTITY = "Category";

    public CategoryAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public PaginatedResponse<Category> getAllCategoriesPaginated(PaginationRequest paginationRequest) {
        // Convierte PaginationRequest a Pageable
        Pageable pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(),
                Sort.by(paginationRequest.getSortDirection() == SortDirection.ASC
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC, paginationRequest.getSortBy()));

        // Consulta la base de datos paginada
        Page<CategoryEntity> categoryEntitiesPage = categoryRepository.findAll(pageable);

        // Mapea las entidades a objetos de dominio
        List<Category> categories = categoryEntitiesPage.getContent().stream()
                .map(categoryEntityMapper::toModel)
                .toList();

        // Retorna la respuesta paginada
        return new PaginatedResponse<>(
                categories,
                categoryEntitiesPage.getNumber(),
                categoryEntitiesPage.getTotalPages(),
                categoryEntitiesPage.getTotalElements()
        );
    }

    @Override
    public Category save(Category category) {
        if(categoryRepository.findByNameContaining(category.getName().toLowerCase()).isPresent()){
            throw new CategoryAlreadyExistsException(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        return categoryEntityMapper.toModel(categoryRepository.save(categoryEntityMapper.toEntity(category)));
    }




    @Override
    public Category getCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByNameContaining(name.toLowerCase())
                .orElseThrow(() ->  new ElementNotFoundException(ENTITY, "name", name));
        return categoryEntityMapper.toModel(categoryEntity);
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        if (categoryEntities.isEmpty()) {
            throw new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        }
        return categoryEntityMapper.toModelList(categoryEntities);
    }

    @Override
    public Category getCategoryById(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(ENTITY, "id", id.toString()));
        return categoryEntityMapper.toModel(categoryEntity);
    }

    @Override
    public Category updateCategory(Category category) {
        if(categoryRepository.findById(category.getId()).isEmpty()){
            throw new ElementNotFoundException(ENTITY, "id", category.getId().toString());
        }
        return categoryEntityMapper.toModel(categoryRepository.save(categoryEntityMapper.toEntity(category)));
    }

    @Override
    public void deleteById(Integer id) {
        if(categoryRepository.findById(id).isEmpty()){
            throw new ElementNotFoundException(ENTITY, "id", id.toString());
        }
        categoryRepository.deleteById(id);
    }

}
