package com.example.booksshop.service;

import com.example.booksshop.dao.BookDao;
import com.example.booksshop.dao.CustomerDao;
import com.example.booksshop.dao.OrderItemDao;
import com.example.booksshop.dto.CartItem;
import com.example.booksshop.entity.*;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDao customerDao;
    private final BookDao bookDao;
    private final CartService cartService;
    private final OrderItemDao orderItemDao;


    @Transactional
    public void saveCustomerOrderItems(Customer customer) {
        Customer customer1 = customerDao.findCustomerByCustomerName(customer.getCustomerName())
                .orElseThrow(EntityExistsException::new);

        Order order = customer1.getOrders().get(0);

        OrderItem orderItem = new OrderItem();

        orderItem.setOrder(order);

        int quantity = cartService.getCartItems()
                .stream()
                .map(CartItem::getQuantity)
                .mapToInt(Integer::intValue)
                .sum();

        orderItem.setQuantity(quantity);
        OrderItem orderItem1 = orderItemDao.save(orderItem);
//        cartService.getCartItems()
//                .stream()
//                .map(c -> toBook(c))
//                .forEach(b -> orderItem.addBook(b));

        List<Book> books = cartService
                .getCartItems()
                .stream()
                .map(this::toBook).collect(Collectors.toList());

        var booklist = bookDao.saveAllAndFlush(books);
        orderItem1.addBooks(booklist);
    }

    private Book toBook(CartItem cartItem) {
        BookId bookId = new BookId();
        bookId.setId(cartItem.getId());
        bookId.setIsbn(cartItem.getIsbn());

        return bookDao.findById(bookId)
                .orElseThrow(EntityExistsException::new);
    }
}
