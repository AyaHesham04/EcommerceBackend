package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("EXPIRABLE")
public class ExpirableProduct extends Product {
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "weight")
    private double weight;

    @Column(name = "requires_shipping")
    private boolean requiresShipping;

    public ExpirableProduct() {}

    public ExpirableProduct(String name, double price, int quantity,
                            LocalDate expiryDate, double weight, boolean requiresShipping) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
        this.requiresShipping = requiresShipping;
    }

    @Override
    public boolean isExpired() {
        return expiryDate != null && LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public boolean requiresShipping() {
        return requiresShipping;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public void setWeight(double weight) { this.weight = weight; }
    public void setRequiresShipping(boolean requiresShipping) { this.requiresShipping = requiresShipping; }
}