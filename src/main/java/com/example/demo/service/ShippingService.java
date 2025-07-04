package com.example.demo.service;

import java.util.List;

public interface ShippingService {
    void shipItems(List<ShippableItem> items);
    double calculateShippingFee(List<ShippableItem> items);
}
