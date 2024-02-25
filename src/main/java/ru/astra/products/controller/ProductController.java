package ru.astra.products.controller;

import org.springframework.web.bind.annotation.*;
import ru.astra.products.domain.ProductRequest;
import ru.astra.products.domain.ProductResponse;
import ru.astra.products.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getList() {
        return productService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<ProductResponse> getList(@PathVariable("userId") Long userId) {
        return productService.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        return productService.save(request);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id,
                                         @RequestBody ProductRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public List<ProductResponse> deleteById(@PathVariable("id") Long id) {
        productService.removeById(id);
        return productService.findAll();
    }
}
