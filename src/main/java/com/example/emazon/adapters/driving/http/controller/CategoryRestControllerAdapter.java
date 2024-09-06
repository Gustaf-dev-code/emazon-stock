package com.example.emazon.adapters.driving.http.controller;

import com.example.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.example.emazon.adapters.driving.http.dto.request.PaginationRequestDto;
import com.example.emazon.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.example.emazon.adapters.driving.http.dto.response.CategoryResponse;
import com.example.emazon.adapters.driving.http.dto.response.PaginatedResponseDto;
import com.example.emazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.example.emazon.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.example.emazon.adapters.driving.http.mapper.IPaginatedResponseMapper;
import com.example.emazon.adapters.driving.http.mapper.IPaginationRequestMapper;
import com.example.emazon.configuration.exceptionhandler.ExceptionResponse;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/category")
@Tag(name = "Category API", description = "API for managing categories")  // Agrupa los endpoints bajo "Category API"

public class CategoryRestControllerAdapter {
    private  final ICategoryServicePort categoryServicePort;
    private final ICategoryResponseMapper categoryResponseMapper;
    private final  ICategoryRequestMapper categoryRequestMapper;

    private final IPaginationRequestMapper paginationRequestMapper;

    private final IPaginatedResponseMapper paginatedResponseMapper;

    public CategoryRestControllerAdapter(ICategoryServicePort categoryServicePort, ICategoryResponseMapper categoryResponseMapper, ICategoryRequestMapper categoryRequestMapper, IPaginationRequestMapper paginationRequestMapper, IPaginatedResponseMapper paginatedResponseMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryResponseMapper = categoryResponseMapper;
        this.categoryRequestMapper = categoryRequestMapper;
        this.paginationRequestMapper = paginationRequestMapper;
        this.paginatedResponseMapper = paginatedResponseMapper;
    }

    @Operation(
            summary = "Create a new category",  // Resumen del endpoint
            description = "This endpoint allows you to create a new category by providing a name and description.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",  // Código de respuesta
                            description = "Category successfully created",  // Descripción de la respuesta
                            content = @Content(
                                    schema = @Schema(implementation = CategoryResponse.class)  // Esquema de la respuesta
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
                    )
            }
    )
    @PostMapping ("/")
    public ResponseEntity<CategoryResponse> save(@RequestBody AddCategoryRequest addCategoryRequest) {
        Category category = categoryServicePort.save(categoryRequestMapper.addRequestToCategory(addCategoryRequest));
        CategoryResponse categoryResponse = categoryResponseMapper.toCategoryResponse(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }


    @Operation(
            summary = "Get category by ID",  // Resumen del método
            description = "Fetches a category by its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved category",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            )
    })
    @GetMapping ("/search/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(categoryServicePort.getCategoryById(id)));
    }


    @Operation(
            summary = "Get category by name",
            description = "Fetches a category by its unique name."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved category",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            )
    })
    @GetMapping ("/search/name/{name}")
    public ResponseEntity<CategoryResponse> getCategoryByName(@PathVariable
                                                              @Parameter(description = "Name of the category to be fetched", example = "Electronics", required = true)
                                                              String name ) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(categoryServicePort.getCategoryByName(name)));
    }

    @Operation(
            summary = "Get all categories with pagination.",
            description = "Fetches a list of all categories with pagination."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of categories with pagination.",
                    content = @Content(schema = @Schema(implementation = PaginatedResponseDto.class))
            )
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<PaginatedResponseDto<CategoryResponse>> getCategoriesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        // Crear PaginationRequestDto con los parámetros recibidos
        PaginationRequestDto paginationRequestDto = new PaginationRequestDto(page, size, sortBy, sortDirection);

        // Mapear PaginationRequestDto a PaginationRequest y obtener categorías paginadas
        PaginationRequest paginationRequest = paginationRequestMapper.toPaginationRequest(paginationRequestDto);
        PaginatedResponse<Category> paginatedCategories = categoryServicePort.getAllCategoriesPaginated(paginationRequest);

        // Mapear PaginatedResponse<Category> a PaginatedResponseDto<CategoryDto>
        PaginatedResponseDto<CategoryResponse> responseDto = paginatedResponseMapper.toPaginatedResponseDto(paginatedCategories);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "Get all categories",
            description = "Fetches a list of all categories."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of categories",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            )
    })
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
