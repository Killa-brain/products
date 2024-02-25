package ru.astra.products.repository;

import ru.astra.products.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Optional<Product> findById(Long id);
    List<Product> findAllByUserId(Long userId);

    List<Product> findAll();

    Product save(Product product);

    boolean update(Product product);

    boolean removeById(Long id);
}
