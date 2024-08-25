package com.example.emazon.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateCategoryRequest {
    private final Integer id;
    private final String name;
    private final String description;

}
