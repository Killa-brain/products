package ru.astra.products.utils;

import ru.astra.products.domain.Product;
import ru.astra.products.domain.ProductRequest;
import ru.astra.products.domain.ProductResponse;
import ru.astra.products.domain.ProductType;

import java.util.ArrayList;
import java.util.List;

public final class Mapper {
    private Mapper() {}

    public static ProductResponse mapToResponse(Product product) {
        return new ProductResponse(product.getAccountNumber(), product.getBalance(), product.getProductType());
    }

    public static List<ProductResponse> mapListResponse(List<Product> products) {
        if (products != null && !products.isEmpty()) {
            return products.stream()
                    .map(Mapper::mapToResponse)
                    .toList();
        }
        return new ArrayList<>();
    }

    public static Product requestToEntity(ProductRequest request) {
        var p = new Product(request.getUserId(),
                request.getAccountNumber(),
                request.getBalance(),
                ProductType.getByValue(request.getProductType()));
        return p;
    }
}
