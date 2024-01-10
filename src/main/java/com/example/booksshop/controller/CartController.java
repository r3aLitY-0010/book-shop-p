package com.example.booksshop.controller;

import com.example.booksshop.entity.Book;
import com.example.booksshop.entity.BookId;
import com.example.booksshop.service.BookService;
import com.example.booksshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final BookService bookService;

    @GetMapping("/add-cart")
    public String addToCart(@RequestParam("id") int id,
                            @RequestParam("isbn") String isbn) {
        BookId bookId = new BookId();
        bookId.setId(id);
        bookId.setIsbn(isbn);

        Book book = bookService.findBookById(bookId);
        cartService.addToCart(book);

        return "redirect:/book/list-books";
    }
}
