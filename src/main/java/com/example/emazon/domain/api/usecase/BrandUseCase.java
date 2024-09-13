package com.example.emazon.domain.api.usecase;

import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.domain.exception.EmptyFieldException;
import com.example.emazon.domain.exception.EntityAlreadyExistsException;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.spi.IBrandPersistentPort;
import com.example.emazon.domain.util.DomainConstants;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistentPort brandPersistentPort;

    public BrandUseCase(IBrandPersistentPort brandPersistentPort) {
        this.brandPersistentPort = brandPersistentPort;
    }

    @Override
    public Brand saveBrand(Brand brand) {
        if(brandPersistentPort.existsByName(brand.getName())){
            throw new EntityAlreadyExistsException("Brand", DomainConstants.Field.NAME.toString(), brand.getName());
        }
        return brandPersistentPort.saveBrand(brand);
    }

    @Override
    public Brand getBrandByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        return brandPersistentPort.getBrandByName(name);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandPersistentPort.getAllBrands();
    }

    @Override
    public Brand getBrandById(Integer id) {
        return brandPersistentPort.getBrandById(id);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        return brandPersistentPort.updateBrand(brand);
    }

    @Override
    public void deleteById(Integer id) {
        brandPersistentPort.deleteById(id);
    }



}
