package com.example.emazon.adapters.driving.http.controller;

import com.example.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.example.emazon.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.example.emazon.adapters.driving.http.dto.response.CategoryResponse;
import com.example.emazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.example.emazon.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.example.emazon.domain.api.ICategoryServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/category")
public class CategoryRestControllerAdapter {
    private  final ICategoryServicePort categoryServicePort;
    private final ICategoryResponseMapper categoryResponseMapper;
    private final  ICategoryRequestMapper categoryRequestMapper;

    public CategoryRestControllerAdapter(ICategoryServicePort categoryServicePort, ICategoryResponseMapper categoryResponseMapper, ICategoryRequestMapper categoryRequestMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryResponseMapper = categoryResponseMapper;
        this.categoryRequestMapper = categoryRequestMapper;
    }

    @PostMapping ("/")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        categoryServicePort.save(categoryRequestMapper.addRequestToCategory(addCategoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping ("/search/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(categoryServicePort.getCategoryById(id)));
    }

    @GetMapping ("/search/name/{name}")
    public ResponseEntity<CategoryResponse> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(categoryServicePort.getCategoryByName(name)));
    }

    @GetMapping ("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories()));
    }

    @PutMapping ("/update")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(categoryServicePort.updateCategory(categoryRequestMapper.updateRequestToCategory(updateCategoryRequest))));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryServicePort.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
