package com.example.emazon.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateCategoryRequest {
    Integer id;
    String name;
    String description;
}
