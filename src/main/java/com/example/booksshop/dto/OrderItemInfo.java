package com.example.booksshop.dto;

import com.example.booksshop.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemInfo {
    private  int orderItemId;
    private double totalPrice;
    private List<Book> books =
            new ArrayList<>();
    private LocalDate orderDate;
}
