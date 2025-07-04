package com.example.demo.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private boolean expired;
    private boolean shippable;
    private double weight;

    public ProductDTO(Long id, String name, double price, int quantity,
                      boolean expired, boolean shippable, double weight) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expired = expired;
        this.shippable = shippable;
        this.weight = weight;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isExpired() { return expired; }
    public boolean isShippable() { return shippable; }
    public double getWeight() { return weight; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setExpired(boolean expired) { this.expired = expired; }
    public void setShippable(boolean shippable) { this.shippable = shippable; }
    public void setWeight(double weight) { this.weight = weight; }
}
