package ru.astra.products.service;

import org.springframework.stereotype.Service;
import ru.astra.products.domain.Product;
import ru.astra.products.domain.ProductRequest;
import ru.astra.products.domain.ProductType;
import ru.astra.products.repository.ProductDao;
import ru.astra.products.utils.Mapper;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found product by id: " + id));
    }

    @Override
    public List<Product> findAllByUserId(Long userId) {
        return productDao.findAllByUserId(userId);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product save(ProductRequest request) {
        validate(request);
        return productDao.save(Mapper.requestToEntity(request));
    }

    @Override
    public Product update(Long id, ProductRequest request) {
        validate(request);
        var product = new Product(id,
                request.getUserId(),
                request.getAccountNumber(),
                request.getBalance(),
                ProductType.getByValue(request.getProductType()));
        var result = productDao.update(product);
        return result ? product : null;
    }

    @Override
    public boolean removeById(Long id) {
        return productDao.removeById(id);
    }

    private void validate(ProductRequest request) {
        if (request.getUserId() == null) {
            throw new IllegalArgumentException("userId mustn't be null");
        }
        if (request.getProductType() == null || checkType(request.getProductType())) {
            throw new IllegalArgumentException("productType must be equal card or account");
        }
    }

    private boolean checkType(String productType) {
        return Arrays.stream(ProductType.values())
                .noneMatch(p -> p.getValue().equals(productType));
    }
}
