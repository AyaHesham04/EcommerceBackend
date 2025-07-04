package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return customer != null ? ResponseEntity.ok(toDto(customer)) : ResponseEntity.notFound().build();
    }

    private CustomerDTO toDto(Customer c) {
        return new CustomerDTO(c.getId(), c.getName(), c.getBalance());
    }
}
