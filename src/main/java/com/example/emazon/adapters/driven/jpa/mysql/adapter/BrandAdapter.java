package com.example.emazon.adapters.driven.jpa.mysql.adapter;

import com.example.emazon.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.example.emazon.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.emazon.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.emazon.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.example.emazon.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.example.emazon.configuration.Constants;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.spi.IBrandPersistentPort;

import java.util.List;

public class BrandAdapter implements IBrandPersistentPort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    public BrandAdapter(IBrandRepository brandRepository, IBrandEntityMapper brandEntityMapper) {
        this.brandRepository = brandRepository;
        this.brandEntityMapper = brandEntityMapper;
    }

    @Override
    public Brand saveBrand(Brand brand) {

        return brandEntityMapper.toModel(brandRepository.save(brandEntityMapper.toEntity(brand)));
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandEntityMapper.toModel(brandRepository.findByName(name).orElseThrow(() -> new ElementNotFoundException("Brand", "name", name)));
    }

    @Override
    public boolean existsByName(String name) {
        return brandRepository.findByName(name).isPresent();
    }

    @Override
    public Brand getBrandById(Integer id) {
        return brandEntityMapper.toModel(brandRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Brand", "id", id.toString())));
    }

    @Override
    public void deleteById(Integer id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        if (brandRepository.findById(brand.getId()).isEmpty()) {
            throw new ElementNotFoundException("Brand", "id", brand.getId().toString());
        }
        return brandEntityMapper.toModel(brandRepository.save(brandEntityMapper.toEntity(brand)));
    }

    @Override
    public List<Brand> getAllBrands() {
        List<BrandEntity> brandEntities = brandRepository.findAll();
        if (brandEntities.isEmpty()) {
            throw new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        }
        return brandEntityMapper.toModelList(brandEntities);
    }
}
