package ru.astra.products.service;

import ru.astra.products.domain.Product;
import ru.astra.products.domain.ProductRequest;
import ru.astra.products.domain.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductResponse findById(Long id);
    List<ProductResponse> findAllByUserId(Long userId);

    List<ProductResponse> findAll();

    ProductResponse save(ProductRequest request);

    ProductResponse update(Long id, ProductRequest product);

    boolean removeById(Long id);
}
