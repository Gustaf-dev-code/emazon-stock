package com.example.emazon.adapters.driving.http.dto.response.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(description = "Response object for brand")
public class BrandResponseDto {
    @Schema(description = "Id of the brand", example = "1", required = true)
    private Integer id;
    @Schema(description = "Name of the brand", example = "Samsung", required = true)
    private String name;
    @Schema(description = "Description of the brand", example = "Korean technology company", required = true)
    private String description;
}
