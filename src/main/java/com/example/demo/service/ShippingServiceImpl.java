package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 5.0;

    @Override
    public void shipItems(List<ShippableItem> items) {
        double totalWeight = 0;
        for (ShippableItem item : items) {
            totalWeight += item.getWeight();
        }
    }

    @Override
    public double calculateShippingFee(List<ShippableItem> items) {
        double totalWeight = items.stream()
                .mapToDouble(ShippableItem::getWeight)
                .sum();
        return totalWeight * SHIPPING_RATE_PER_KG;
    }
}