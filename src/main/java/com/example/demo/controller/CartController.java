package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.CartItemDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        try {
            cartService.addToCart(productId, quantity);
            return ResponseEntity.ok("Product added to cart");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return ResponseEntity.ok("Product removed from cart");
    }

    @GetMapping
    public CartDTO getCart() {
        Cart cart = cartService.getCart();
        return new CartDTO(
                cart.getItems().stream().map(this::toCartItemDto).collect(Collectors.toList()),
                cart.getSubtotal()
        );
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam Long customerId) {
        try {
            cartService.checkout(customerId);
            return ResponseEntity.ok("Checkout successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private CartItemDTO toCartItemDto(CartItem item) {
        Product p = item.getProduct();
        ProductDTO productDTO = new ProductDTO(
                p.getId(), p.getName(), p.getPrice(), p.getQuantity(),
                p.isExpired(), p.requiresShipping(), p.getWeight()
        );
        return new CartItemDTO(productDTO, item.getQuantity(), item.getTotalPrice());
    }
}