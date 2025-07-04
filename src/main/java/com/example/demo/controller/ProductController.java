package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        return product != null ? ResponseEntity.ok(toDto(product)) : ResponseEntity.notFound().build();
    }

    private ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.isExpired(),
                product.requiresShipping(),
                product.getWeight()
        );
    }
}