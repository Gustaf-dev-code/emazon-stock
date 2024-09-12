package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Brand;
import java.util.List;

public interface IBrandPersistentPort {
    Brand saveBrand(Brand brand);
    Brand getBrandByName(String name);
    Brand getBrandById(Integer id);
    List<Brand> getAllBrands();
    Brand updateBrand(Brand brand);
    void deleteById(Integer id);

    boolean existsByName(String name);

}
