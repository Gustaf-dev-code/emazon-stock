package com.example.emazon.adapters.driving.http.dto.request.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "Request object for updating a brand")
public class UpdateBrandRequestDto {
    @Schema(description = "Id of the brand", example = "1", required = true)
    Integer id;
    @Schema(description = "Name of the brand", example = "Samsung", required = true)
    String name;
    @Schema(description = "Description of the brand", example = "Korean technology company", required = true)
    String description;
}
