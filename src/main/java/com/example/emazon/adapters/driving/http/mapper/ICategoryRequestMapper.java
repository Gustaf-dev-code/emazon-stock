package com.example.emazon.adapters.driving.http.mapper;

import com.example.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.example.emazon.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.example.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Category updateRequestToCategory(UpdateCategoryRequest updateCategoryRequest);
}
