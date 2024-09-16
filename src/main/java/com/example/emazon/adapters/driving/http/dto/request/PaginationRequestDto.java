package com.example.emazon.adapters.driving.http.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request object for pagination")
public class PaginationRequestDto {
        @Schema(description = "Page number", example = "1",  requiredMode = Schema.RequiredMode.REQUIRED)
        private int page;
        @Schema(description = "Number of items per page", example = "10",  requiredMode = Schema.RequiredMode.REQUIRED)
        private int size;
        @Schema(description = "Allows sorting by atrribute type.", example = "name",  requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String sortBy;
        @Schema(description = "Allows sorting in Asc or Desc order. ", example = "asc",  requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String sortDirection;

}
