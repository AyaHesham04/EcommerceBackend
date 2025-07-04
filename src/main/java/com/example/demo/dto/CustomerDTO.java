package com.example.demo.dto;

public class CustomerDTO {
    private Long id;
    private String name;
    private double balance;

    public CustomerDTO(Long id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getBalance() { return balance; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBalance(double balance) { this.balance = balance; }
}
