package com.example.emazon.adapters.driven.jpa.mysql.adapter;

import com.example.emazon.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.example.emazon.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.emazon.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.emazon.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.example.emazon.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.example.emazon.configuration.Constants;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;
import com.example.emazon.domain.model.SortDirection;
import com.example.emazon.domain.spi.IBrandPersistentPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class BrandAdapter implements IBrandPersistentPort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    private static final String ENTITY = "Brand";

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
        return brandEntityMapper.toModel(brandRepository.findByName(name).orElseThrow(() -> new ElementNotFoundException(ENTITY, "name", name)));
    }

    @Override
    public boolean existsByName(String name) {
        return brandRepository.findByName(name).isPresent();
    }

    @Override
    public Brand getBrandById(Integer id) {
        return brandEntityMapper.toModel(brandRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(ENTITY, "id", id.toString())));
    }

    @Override
    public void deleteById(Integer id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        if (brandRepository.findById(brand.getId()).isEmpty()) {
            throw new ElementNotFoundException(ENTITY, "id", brand.getId().toString());
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

    @Override
    public PaginatedResponse<Brand> getAllBrandsPaginated(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(),
                Sort.by(paginationRequest.getSortDirection() == SortDirection.ASC
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC, paginationRequest.getSortBy()));

        Page<BrandEntity> brandEntitiesPage = brandRepository.findAll(pageable);

        List<Brand> brands = brandEntitiesPage.getContent().stream()
                .map(brandEntityMapper::toModel)
                .toList();

        return new PaginatedResponse<>(
                brands,
                brandEntitiesPage.getNumber(),
                brandEntitiesPage.getTotalPages(),
                brandEntitiesPage.getTotalElements());
    }
}
