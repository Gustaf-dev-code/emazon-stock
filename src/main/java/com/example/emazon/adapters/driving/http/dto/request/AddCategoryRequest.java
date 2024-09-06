package com.example.emazon.adapters.driving.http.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Getter
@Builder //Este es un patrón de diseño que se utiliza para crear objetos complejos, en este caso, el objeto AddCategoryRequest
@Schema(description = "Request object for adding a new category")
public class AddCategoryRequest{
        @Schema(description = "Name of the category", example = "Electronics", required = true)
        String name;
        @Schema(description = "Description of the category", example = "Devices and gadgets", required = true)
        String description;


}
