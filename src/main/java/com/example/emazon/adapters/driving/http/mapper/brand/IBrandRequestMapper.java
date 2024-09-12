package com.example.emazon.adapters.driving.http.mapper.brand;

import com.example.emazon.adapters.driving.http.dto.request.brand.AddBrandRequestDto;
import com.example.emazon.adapters.driving.http.dto.request.brand.UpdateBrandRequestDto;
import com.example.emazon.domain.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {
    // Mapea de DTO a objeto de dominio

    Brand addRequestToBrand(AddBrandRequestDto brandRequestDto);

    Brand updateRequestToBrand(UpdateBrandRequestDto brandRequestDto);


}
