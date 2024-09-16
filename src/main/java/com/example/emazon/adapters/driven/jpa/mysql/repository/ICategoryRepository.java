package com.example.emazon.adapters.driven.jpa.mysql.repository;

import com.example.emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer>{
    Optional<CategoryEntity> findByNameContaining(String name);

    Optional<CategoryEntity>  findById(Integer id);

}
