package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final Cart cart = new Cart();

    @Autowired private ProductRepository productRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private ShippingService shippingService;

    @Override
    public void addToCart(Long productId, int qty) throws Exception {
        Product p = productRepo.findById(productId).orElseThrow(() -> new Exception("Product not found"));
//        if (p.isExpired()) throw new Exception(p.getName() + " is expired");
//        if (p.getQuantity() < qty) throw new Exception("Not enough stock for " + p.getName());
        cart.addItem(p, qty);
    }

    @Override
    public void removeFromCart(Long productId) {
        cart.removeItem(productId);
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void checkout(Long customerId) throws Exception {
        if (cart.isEmpty()) throw new Exception("Cart is empty");

        Customer c = customerRepo.findById(customerId)
                .orElseThrow(() -> new Exception("Customer not found"));

        //validation for stock & expiry
        for (CartItem it : cart.getItems()) {
            Product p = productRepo.findById(it.getProduct().getId())
                    .orElseThrow(() -> new Exception("Product not found"));
            if (p.isExpired()) throw new Exception(p.getName() + " is expired");
            if (p.getQuantity() < it.getQuantity()) throw new Exception(p.getName() + " out of stock");
        }

        double subtotal = cart.getSubtotal();

        List<ShippableItem> toShip = cart.getItems().stream()
                .filter(it -> it.getProduct().requiresShipping())
                .map(it -> new ShippableItem() {
                    public String getName() { return it.getProduct().getName(); }
                    public double getWeight() {
                        // weight per unit * qty
                        return it.getProduct().getWeight() * it.getQuantity();
                    }
                })
                .collect(Collectors.toList());

        double shippingFee = shippingService.calculateShippingFee(toShip);

        if (c.getBalance() < subtotal + shippingFee)
            throw new Exception("Insufficient balance");

        System.out.println("CONSOLE OUTPUT");
        //send to shipping service
        if (!toShip.isEmpty()) {
            System.out.println("** Shipment notice **");
            double totalWeightKg = toShip.stream()
                    .mapToDouble(ShippableItem::getWeight)
                    .sum();
            System.out.printf("Total package weight %.1fkg%n", totalWeightKg);
            shippingService.shipItems(toShip);
        }

        // calculate balance and update stocks
        for (CartItem it : cart.getItems()) {
            Product p = productRepo.findById(it.getProduct().getId()).get();
            p.setQuantity(p.getQuantity() - it.getQuantity());
            productRepo.save(p);
        }
        c.setBalance(c.getBalance() - (subtotal + shippingFee));
        customerRepo.save(c);

        System.out.println("** Checkout receipt **");
        cart.getItems().forEach(it ->
                System.out.printf("%dx %s %.0f%n", it.getQuantity(), it.getProduct().getName(),
                        it.getProduct().getPrice() * it.getQuantity()));
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFee);
        System.out.printf("Amount %.0f%n", subtotal + shippingFee);

        cart.clear();
    }
}
