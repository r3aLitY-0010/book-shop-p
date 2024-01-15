package com.example.booksshop.controller;

import com.example.booksshop.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BookService bookService;

    @RequestMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("books", bookService.listAllBooks());
        return "home";
    }
}
