package ru.astra.products.service;

import ru.astra.products.domain.Product;
import ru.astra.products.domain.ProductRequest;
import ru.astra.products.domain.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product findById(Long id);
    List<Product> findAllByUserId(Long userId);

    List<Product> findAll();

    Product save(ProductRequest request);

    Product update(Long id, ProductRequest product);

    boolean removeById(Long id);
}
