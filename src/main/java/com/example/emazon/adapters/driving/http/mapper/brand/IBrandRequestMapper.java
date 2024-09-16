package com.example.emazon.adapters.driving.http.mapper.brand;

import com.example.emazon.adapters.driving.http.dto.request.brand.AddBrandRequestDto;
import com.example.emazon.adapters.driving.http.dto.request.brand.UpdateBrandRequestDto;
import com.example.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {
    // Mapea de DTO a objeto de dominio
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Brand addRequestToBrand(AddBrandRequestDto brandRequestDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Brand updateRequestToBrand(UpdateBrandRequestDto brandRequestDto);


}
