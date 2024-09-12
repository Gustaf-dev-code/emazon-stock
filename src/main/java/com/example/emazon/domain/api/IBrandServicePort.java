package com.example.emazon.domain.api;

import com.example.emazon.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {
    Brand saveBrand(Brand brand);
    Brand getBrandByName(String name);
    List<Brand> getAllBrands();
    Brand getBrandById(Integer id);
    Brand updateBrand(Brand brand);
    void deleteById(Integer id);
}
