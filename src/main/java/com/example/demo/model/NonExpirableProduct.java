package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("NON_EXPIRABLE")
public class NonExpirableProduct extends Product {
    @Column(name = "weight")
    private double weight;

    @Column(name = "requires_shipping")
    private boolean requiresShipping;

    public NonExpirableProduct() {}

    public NonExpirableProduct(String name, double price, int quantity,
                               double weight, boolean requiresShipping) {
        super(name, price, quantity);
        this.weight = weight;
        this.requiresShipping = requiresShipping;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean requiresShipping() {
        return requiresShipping;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) { this.weight = weight; }
    public void setRequiresShipping(boolean requiresShipping) { this.requiresShipping = requiresShipping; }
}