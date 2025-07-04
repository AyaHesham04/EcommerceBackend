package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        // Create sample products
        ExpirableProduct cheese = new ExpirableProduct("Cheese", 15.99, 10,
                LocalDate.now().plusDays(-1), 0.5, true);
        productService.save(cheese);

        ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 8.99, 20,
                LocalDate.now().plusDays(30), 0.3, true);
        productService.save(biscuits);

        NonExpirableProduct tv = new NonExpirableProduct("TV", 799.99, 0, 15.0, true);
        productService.save(tv);

        NonExpirableProduct mobile = new NonExpirableProduct("Mobile", 699.99, 8, 0.5, true);
        productService.save(mobile);

        NonExpirableProduct scratchCard = new NonExpirableProduct("Mobile Scratch Card", 25.0, 100, 0.0, false);
        productService.save(scratchCard);

        // Sample customer
        Customer customer1 = new Customer("Aya Hesham", 2000.0);
        customerService.save(customer1);

        System.out.println("Sample data initialized!");
    }
}
