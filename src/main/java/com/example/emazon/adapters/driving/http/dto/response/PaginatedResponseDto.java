package com.example.emazon.adapters.driving.http.dto.response;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseDto<T> {
    private List<T> items;
    private int currentPage;
    private int totalPages;
    private long totalItems;

}
