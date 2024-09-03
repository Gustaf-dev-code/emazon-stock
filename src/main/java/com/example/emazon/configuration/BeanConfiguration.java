package com.example.emazon.configuration;

import com.example.emazon.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.example.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.example.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.api.usecase.CategoryUseCase;
import com.example.emazon.domain.spi.ICategoryPersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistentPort categoryPersistentPort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistentPort());
    }
}
