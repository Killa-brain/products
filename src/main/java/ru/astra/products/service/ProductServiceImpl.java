package ru.astra.products.service;

import org.springframework.stereotype.Service;
import ru.astra.products.domain.Product;
import ru.astra.products.domain.ProductRequest;
import ru.astra.products.domain.ProductResponse;
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
    public ProductResponse findById(Long id) {
        var product = productDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found product by id: " + id));
        return Mapper.mapToResponse(product);
    }

    @Override
    public List<ProductResponse> findAllByUserId(Long userId) {
        var listProducts = productDao.findAllByUserId(userId);
        return Mapper.mapListResponse(listProducts);
    }

    @Override
    public List<ProductResponse> findAll() {
        var listProducts = productDao.findAll();
        return Mapper.mapListResponse(listProducts);
    }

    @Override
    public ProductResponse save(ProductRequest request) {
        validate(request);
        var product = productDao.save(Mapper.requestToEntity(request));
        return Mapper.mapToResponse(product);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        validate(request);
        var product = new Product(id,
                request.getUserId(),
                request.getAccountNumber(),
                request.getBalance(),
                ProductType.getByValue(request.getProductType()));
        var result = productDao.update(product);
        return result ? Mapper.mapToResponse(product) : null;
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
