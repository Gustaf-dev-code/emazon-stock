package com.example.emazon.adapters.driving.http.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Request object for updating a category")
public class UpdateCategoryRequest {
    @Schema(description = "Id of the category", example = "1", required = true)
    Integer id;
    @Schema(description = "Name of the category", example = "Devices and gadgets", required = true)
    String name;
    @Schema(description = "Description of the category", example = "Devices and gadgets", required = true)
    String description;
}
