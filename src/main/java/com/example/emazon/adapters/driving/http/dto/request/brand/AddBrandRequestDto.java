package com.example.emazon.adapters.driving.http.dto.request.brand;

import com.example.emazon.domain.util.DomainConstants;
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
    @NotBlank(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(min = DomainConstants.FIELD_NAME_MIN_SIZE, max = DomainConstants.FIELD_NAME_MAX_SIZE, message = DomainConstants.FIELD_NAME_SIZE_MESSAGE)
    @Schema(description = "Name of the brand", example = "Samsung", requiredMode = Schema.RequiredMode.REQUIRED)
    String name;

    @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(min = DomainConstants.FIELD_DESCRIPTION_MIN_SIZE, max = DomainConstants.FIELD_DESCRIPTION_BRAND_MAX_SIZE, message = DomainConstants.FIELD_DESCRIPTION_BRAND_SIZE_MESSAGE)
    @Schema(description = "Description of the brand", example = "Korean technology company", requiredMode = Schema.RequiredMode.REQUIRED)
    String description;
}
