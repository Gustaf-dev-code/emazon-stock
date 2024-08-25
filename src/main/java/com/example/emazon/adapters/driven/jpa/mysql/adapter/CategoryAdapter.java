package com.example.emazon.adapters.driven.jpa.mysql.adapter;


import com.example.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.example.emazon.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistException;
import com.example.emazon.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.emazon.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.example.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.ICategoryPersistentPort;

import java.util.List;

public class CategoryAdapter implements ICategoryPersistentPort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    public CategoryAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public Category save(Category category) {
        if(category.getId() != null){
            if(categoryRepository.findById(category.getId()).isPresent()){
                throw new CategoryAlreadyExistException("Category already exist with same ID");
            }
        }
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new CategoryAlreadyExistException("Category already exist");
        }
        return categoryEntityMapper.toModel(categoryRepository.save(categoryEntityMapper.toEntity(category)));
    }

    @Override
    public Category getCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByNameContaining(name.toLowerCase())
                .orElseThrow(() -> new ElementNotFoundException("Category not found with name: " + name));
        return categoryEntityMapper.toModel(categoryEntity);
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        if (categoryEntities.isEmpty()) {
            throw new NoDataFoundException("No data found");
        }
        return categoryEntityMapper.toModelList(categoryEntities);
    }

    @Override
    public Category getCategoryById(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Category not found with id: " + id));
        return categoryEntityMapper.toModel(categoryEntity);
    }

    @Override
    public Category updateCategory(Category category) {
        if(categoryRepository.findById(category.getId()).isEmpty()){
            throw new ElementNotFoundException("Category not found with id: " + category.getId());
        }
        return categoryEntityMapper.toModel(categoryRepository.save(categoryEntityMapper.toEntity(category)));
    }

    @Override
    public void deleteById(Integer id) {
        if(categoryRepository.findById(id).isEmpty()){
            throw new ElementNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
