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

    @ManyToMany
    private List<Book> books = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "order_id_fk")
    private Order order;

    private int quantity;

    public void addBook(Book book) {
        book.getOrderItems().add(this);
        this.books.add(book);
    }

    public void addBooks(List<Book> books){
        for (Book book:books){
            book.getOrderItems().add(this);
        }
        this.books=books;
    }
}
