package com.example.emazon.adapters.driving.http.mapper;

import com.example.emazon.adapters.driving.http.dto.request.PaginationRequestDto;
import com.example.emazon.domain.model.PaginationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IPaginationRequestMapper {
    // Mapea de DTO a objeto de dominio
    @Mapping(source = "page", target = "page")
    @Mapping(source = "size", target = "size")
    @Mapping(source = "sortBy", target = "sortBy")
    @Mapping(source = "sortDirection", target = "sortDirection")
    PaginationRequest toPaginationRequest(PaginationRequestDto paginationRequestDto);

}
