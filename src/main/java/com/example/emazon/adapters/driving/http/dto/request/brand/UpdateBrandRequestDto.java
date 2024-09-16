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
    @Schema(description = "Id of the brand", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer id;
    @Schema(description = "Name of the brand", example = "Samsung",  requiredMode = Schema.RequiredMode.REQUIRED)
    String name;
    @Schema(description = "Description of the brand", example = "Korean technology company", requiredMode = Schema.RequiredMode.REQUIRED)
    String description;
}
