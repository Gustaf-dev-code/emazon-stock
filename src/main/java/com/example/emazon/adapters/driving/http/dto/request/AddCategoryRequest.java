package com.example.emazon.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Getter
@Builder //Este es un patrón de diseño que se utiliza para crear objetos complejos, en este caso, el objeto AddCategoryRequest
public class AddCategoryRequest{

        String name;
        String description;


}
