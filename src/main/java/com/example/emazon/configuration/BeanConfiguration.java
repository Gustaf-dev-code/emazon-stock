package com.example.emazon.configuration;

import com.example.emazon.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import com.example.emazon.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.example.emazon.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.example.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.example.emazon.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.example.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.api.usecase.BrandUseCase;
import com.example.emazon.domain.api.usecase.CategoryUseCase;
import com.example.emazon.domain.spi.IBrandPersistentPort;
import com.example.emazon.domain.spi.ICategoryPersistentPort;
import com.example.emazon.domain.util.PaginationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public ICategoryPersistentPort categoryPersistentPort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public PaginationValidator paginationValidator() {
        return new PaginationValidator();
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistentPort(), paginationValidator());
    }

    @Bean
    public IBrandPersistentPort brandPersistentPort(){
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistentPort(), paginationValidator());
    }
}
