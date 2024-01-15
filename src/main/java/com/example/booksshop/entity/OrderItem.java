package com.example.booksshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "orderItem")
    private List<Book> books = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "order_id_fk")
    private Order order;

    private int quantity;

    public void addBook(Book book) {
        book.setOrderItem(this);
        this.books.add(book);
    }
}
