package com.example.emazon.adapters.driven.jpa.mysql.mapper;

import com.example.emazon.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.example.emazon.domain.model.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    Brand toModel(BrandEntity brandEntity);//convert entity to model

    BrandEntity toEntity(Brand brand);//convert model to entity

    List<Brand> toModelList(List<BrandEntity> brandEntities);//convert list of entities to list of models
}
