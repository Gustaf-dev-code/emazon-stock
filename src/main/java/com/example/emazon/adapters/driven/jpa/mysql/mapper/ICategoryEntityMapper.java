package com.example.emazon.adapters.driven.jpa.mysql.mapper;

import com.example.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.example.emazon.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {

    Category toModel (CategoryEntity categoryEntity);

    CategoryEntity toEntity (Category category);
    List<Category> toModelList(List<CategoryEntity> categoryEntities);
}
