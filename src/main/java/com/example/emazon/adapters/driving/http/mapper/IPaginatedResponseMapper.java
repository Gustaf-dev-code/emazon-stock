package com.example.emazon.adapters.driving.http.mapper;

import com.example.emazon.adapters.driving.http.dto.response.CategoryResponse;
import com.example.emazon.adapters.driving.http.dto.response.PaginatedResponseDto;
import com.example.emazon.adapters.driving.http.dto.response.brand.BrandResponseDto;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.model.PaginatedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IPaginatedResponseMapper {
    // Mapea de PaginatedResponse<Category> a PaginatedResponseDto<CategoryDto>
    @Mapping(source = "currentPage", target = "currentPage")
    @Mapping(source = "totalPages", target = "totalPages")
    @Mapping(source = "totalItems", target = "totalItems")
    PaginatedResponseDto<CategoryResponse> toCategoryPaginatedResponseDto(PaginatedResponse<Category> response);

    @Mapping(source = "currentPage", target = "currentPage")
    @Mapping(source = "totalPages", target = "totalPages")
    @Mapping(source = "totalItems", target = "totalItems")
    PaginatedResponseDto<BrandResponseDto> toBrandPaginatedResponseDto(PaginatedResponse<Brand> response);

}
