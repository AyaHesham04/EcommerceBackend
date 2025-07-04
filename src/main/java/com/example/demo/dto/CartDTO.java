package com.example.demo.dto;

import java.util.List;

public class CartDTO {
    private List<CartItemDTO> items;
    private double subtotal;

    public CartDTO(List<CartItemDTO> items, double subtotal) {
        this.items = items;
        this.subtotal = subtotal;
    }

    public List<CartItemDTO> getItems() { return items; }
    public double getSubtotal() { return subtotal; }

    public void setItems(List<CartItemDTO> items) { this.items = items; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
