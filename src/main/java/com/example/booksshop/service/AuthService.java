package com.example.booksshop.service;

import com.example.booksshop.dao.CustomerDao;
import com.example.booksshop.dao.OrderDao;
import com.example.booksshop.dao.RoleDao;
import com.example.booksshop.dto.CustomerOrder;
import com.example.booksshop.entity.Customer;
import com.example.booksshop.entity.Order;
import com.example.booksshop.entity.Role;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final CustomerDao customerDao;
    private final RoleDao roleDao;
    private final OrderDao orderDao;

    @Transactional
    public void register(Customer customer, Order order) {
        Role role = roleDao.findRoleByRoleName("ROLE_USER")
                .orElseThrow(EntityNotFoundException::new);

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.addRoles(role);

        Order managedOrder = orderDao.save(order);

        customer.addOrder(managedOrder);

        customerDao.save(customer);
    }

    public CustomerOrder findCustomerInfoByCustomerName(String customerName) {
        return customerDao.customerOrderInfo(customerName)
                .orElseThrow(EntityNotFoundException::new);
    }
}
