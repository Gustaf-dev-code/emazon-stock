package com.example.emazon.adapters.driving.http.controller.brand;

import com.example.emazon.adapters.driving.http.dto.request.brand.AddBrandRequestDto;
import com.example.emazon.adapters.driving.http.dto.request.brand.UpdateBrandRequestDto;
import com.example.emazon.adapters.driving.http.dto.response.brand.BrandResponseDto;
import com.example.emazon.adapters.driving.http.mapper.brand.IBrandResponseMapper;
import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.adapters.driving.http.mapper.brand.IBrandRequestMapper;
import com.example.emazon.domain.model.Brand;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@Tag(name = "Brand API", description = "API for managing brands")
public class BrandRestControllerAdapter {

    private final IBrandServicePort brandServicePort;
    private final IBrandResponseMapper brandResponseMapper;
    private final IBrandRequestMapper brandRequestMapper;

    public BrandRestControllerAdapter(IBrandServicePort brandServicePort, IBrandResponseMapper brandResponseMapper, IBrandRequestMapper brandRequestMapper) {
        this.brandServicePort = brandServicePort;
        this.brandResponseMapper = brandResponseMapper;
        this.brandRequestMapper = brandRequestMapper;
    }

    // Endpoints
    @PostMapping("/")
    public ResponseEntity<BrandResponseDto> saveBrand (@RequestBody AddBrandRequestDto brandRequestDto) {
        Brand brand = brandRequestMapper.addRequestToBrand(brandRequestDto);
        Brand savedBrand = brandServicePort.saveBrand(brand);
        BrandResponseDto brandResponseDto = brandResponseMapper.toBrandResponse(savedBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandResponseDto);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<BrandResponseDto> getBrandById(@PathVariable Integer id) {
        Brand brand = brandServicePort.getBrandById(id);
        BrandResponseDto brandResponseDto = brandResponseMapper.toBrandResponse(brand);
        return ResponseEntity.ok(brandResponseDto);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<BrandResponseDto> getBrandByName(@PathVariable String name) {
        Brand brand = brandServicePort.getBrandByName(name);
        BrandResponseDto brandResponseDto = brandResponseMapper.toBrandResponse(brand);
        return ResponseEntity.ok(brandResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<BrandResponseDto>> getAllBrands() {
        List<Brand> brand = brandServicePort.getAllBrands();
        List<BrandResponseDto> brandResponseDto = brandResponseMapper.toBrandResponseList(brand);
        return ResponseEntity.ok(brandResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<BrandResponseDto> updateBrand(@RequestBody UpdateBrandRequestDto brandRequestDto) {
        return ResponseEntity.ok(brandResponseMapper.toBrandResponse(brandServicePort.updateBrand(brandRequestMapper.updateRequestToBrand(brandRequestDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandServicePort.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
