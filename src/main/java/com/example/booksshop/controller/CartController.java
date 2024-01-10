package com.example.booksshop.controller;

import com.example.booksshop.entity.Book;
import com.example.booksshop.entity.BookId;
import com.example.booksshop.service.BookService;
import com.example.booksshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                            @RequestParam("isbn") String isbn,
                            @RequestParam("page") String page) {
        BookId bookId = new BookId();
        bookId.setId(id);
        bookId.setIsbn(isbn);

        Book book = bookService.findBookById(bookId);
        cartService.addToCart(book);

        if (page.equals("bookList")) {
            return "redirect:/book/list-books";
        } else {
          return "redirect:/book/book-details?id=" + id + "&isbn=" + isbn;
        }
    }

    @GetMapping("/view-cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "viewCart";
    }
}
