package com.example.demo.service;

import com.example.demo.model.Cart;

public interface CartService {
    void addToCart(Long productId, int quantity) throws Exception;
    void removeFromCart(Long productId);
    Cart getCart();
    void checkout(Long customerId) throws Exception;
}
