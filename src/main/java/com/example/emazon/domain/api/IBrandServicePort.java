package com.example.emazon.domain.api;

import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;

import java.util.List;

public interface IBrandServicePort {
    Brand saveBrand(Brand brand);
    Brand getBrandByName(String name);
    List<Brand> getAllBrands();
    PaginatedResponse<Brand> getAllBrandsPaginated(PaginationRequest paginationRequest);
    Brand getBrandById(Integer id);
    Brand updateBrand(Brand brand);
    void deleteById(Integer id);
}
