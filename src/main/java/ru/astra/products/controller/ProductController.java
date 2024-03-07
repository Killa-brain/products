package ru.astra.products.controller;

import org.springframework.web.bind.annotation.*;
import ru.astra.products.domain.ProductRequest;
import ru.astra.products.domain.ProductResponse;
import ru.astra.products.service.ProductService;
import ru.astra.products.utils.Mapper;

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
        return Mapper.mapListResponse(productService.findAll());
    }

    @GetMapping("/user/{userId}")
    public List<ProductResponse> getList(@PathVariable("userId") Long userId) {
        return Mapper.mapListResponse(productService.findAllByUserId(userId));
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id) {
        return Mapper.mapToResponse(productService.findById(id));
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        return Mapper.mapToResponse(productService.save(request));
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id,
                                         @RequestBody ProductRequest request) {
        return Mapper.mapToResponse(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public List<ProductResponse> deleteById(@PathVariable("id") Long id) {
        productService.removeById(id);
        return Mapper.mapListResponse(productService.findAll());
    }
}
