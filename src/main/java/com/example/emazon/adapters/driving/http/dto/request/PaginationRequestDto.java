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
        @Schema(description = "Page number", example = "1", required = true)
        private int page;
        @Schema(description = "Number of items per page", example = "10", required = true)
        private int size;
        @Schema(description = "Allows sorting by atrribute type.", example = "name", required = false)
        private String sortBy;
        @Schema(description = "Allows sorting in Asc or Desc order. ", example = "asc", required = false)
        private String sortDirection;

}
