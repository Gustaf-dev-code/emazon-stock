package com.example.emazon.domain.api;

import com.example.emazon.domain.model.Product;

public interface IProductServicePort {
    Product save(Product product);//
    Iterable<Product> findAll();//List<Product> findAll();
    Product findById(Integer id); //Optional<Product> findById(Integer id);
    void deleteById(Integer id);//dele a specific product
}
