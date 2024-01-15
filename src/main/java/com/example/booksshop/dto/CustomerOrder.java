package com.example.booksshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CustomerOrder {
    private String customerName;
    private String email;
    private String phoneNumber;
    private LocalDate orderDate;
    private String billingAddress;
    private String shippingAddress;

    public CustomerOrder(String customerName, String email, String phoneNumber, LocalDate orderDate, String billingAddress, String shippingAddress) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }
}
