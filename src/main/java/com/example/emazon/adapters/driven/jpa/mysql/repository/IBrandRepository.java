package com.example.emazon.adapters.driven.jpa.mysql.repository;

import com.example.emazon.adapters.driven.jpa.mysql.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Integer> {
    Optional<BrandEntity> findByName(String name);
    Optional<BrandEntity> findById(Integer id);
}
