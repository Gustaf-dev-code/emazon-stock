package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;

import java.util.List;

public interface IBrandPersistentPort {
    Brand saveBrand(Brand brand);
    Brand getBrandByName(String name);
    Brand getBrandById(Integer id);
    List<Brand> getAllBrands();
    PaginatedResponse<Brand> getAllBrandsPaginated(PaginationRequest paginationRequest);
    Brand updateBrand(Brand brand);
    void deleteById(Integer id);

    boolean existsByName(String name);


}
