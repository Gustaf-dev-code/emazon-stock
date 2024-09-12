package com.example.emazon.adapters.driving.http.dto.request.brand;

import com.example.emazon.domain.util.BrandConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "Request object for adding a new brand")
public class AddBrandRequestDto {
    @NotBlank(message = BrandConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(min = BrandConstants.FIELD_NAME_MIN_SIZE, max = BrandConstants.FIELD_NAME_MAX_SIZE, message = BrandConstants.FIELD_NAME_SIZE_MESSAGE)
    @Schema(description = "Name of the brand", example = "Samsung", required = true)
    String name;

    @NotBlank(message = BrandConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(min = BrandConstants.FIELD_DESCRIPTION_MIN_SIZE, max = BrandConstants.FIELD_DESCRIPTION_MAX_SIZE, message = BrandConstants.FIELD_DESCRIPTION_SIZE_MESSAGE)
    @Schema(description = "Description of the brand", example = "Korean technology company", required = true)
    String description;
}
