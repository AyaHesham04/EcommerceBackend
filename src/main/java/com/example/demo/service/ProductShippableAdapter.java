package com.example.demo.service;

import com.example.demo.model.Product;

public class ProductShippableAdapter implements ShippableItem {
    private final Product product;

    public ProductShippableAdapter(Product product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getWeight() {
        return product.getWeight();
    }
}