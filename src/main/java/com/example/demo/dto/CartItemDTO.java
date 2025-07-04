package com.example.demo.dto;

public class CartItemDTO {
    private ProductDTO product;
    private int quantity;
    private double totalPrice;

    public CartItemDTO(ProductDTO product, int quantity, double totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public ProductDTO getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }

    public void setProduct(ProductDTO product) { this.product = product; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
