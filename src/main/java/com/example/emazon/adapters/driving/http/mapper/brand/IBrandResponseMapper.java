package com.example.emazon.adapters.driving.http.mapper.brand;

import com.example.emazon.adapters.driving.http.dto.response.brand.BrandResponseDto;
import com.example.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {

    BrandResponseDto toBrandResponse(Brand brand);
    List<BrandResponseDto> toBrandResponseList(List<Brand> brands);
}
