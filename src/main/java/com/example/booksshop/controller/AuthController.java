package com.example.booksshop.controller;

import com.example.booksshop.entity.Customer;
import com.example.booksshop.entity.Order;
import com.example.booksshop.entity.PaymentMethod;
import com.example.booksshop.service.AuthService;
import com.example.booksshop.service.CartService;
import com.example.booksshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final CartService cartService;
    private final CustomerService customerService;

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    // tricky code
    private Customer customer;

    @PostMapping("/save-customer")
    public String saveCustomer(@RequestParam("billingAddress") String billingAddress,
                               @RequestParam("shippingAddress") String shippingAddress,
                               @RequestParam("payment") PaymentMethod method,
                               @ModelAttribute("totalPrice") double totalPrice,
                               Customer customer,
                               BindingResult result) {
        Order order = new Order(LocalDate.now(), billingAddress, shippingAddress, method, totalPrice);

        if (result.hasErrors()) {
            return "register";
        }

        authService.register(customer, order);
        this.customer = customer;

        return "redirect:/auth/info";
    }

//    @GetMapping("/info")
//    public String checkoutInfo(ModelMap map, @ModelAttribute("totalPrice") double totalPrice) {
//        map.put("cartItems", cartService.getCartItems());
//        map.put("totalPrice", totalPrice);
//        return "info";
//    }

    @GetMapping("/info")
    public ModelAndView checkoutInfo(ModelMap map, @ModelAttribute("totalPrice") double totalPrice) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("cartItems", cartService.getCartItems());
        mv.addObject("totalPrice", totalPrice);
        mv.addObject("customerInfo", authService
                .findCustomerInfoByCustomerName(customer.getCustomerName()));
        mv.setViewName("info");
        return mv;
    }

    @GetMapping("/login")
    public String login() {
        if (Objects.isNull(customer)) {
            return "login";
        } else {
            customerService.saveCustomerOrderItems(customer);
//            System.out.println(customer);
            return "login";
        }
    }

    @ModelAttribute("totalPrice")
    public double totalAmount() {
        Optional<Double> optionalDouble = cartService.getCartItems()
                .stream()
                .map(c -> c.getQuantity() * c.getPrice())
                .reduce((a, b) -> a + b);

        return optionalDouble.orElse(0.0);
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
