package com.example.booksshop.dao;

import com.example.booksshop.dto.OrderItemInfo;
import com.example.booksshop.entity.Book;
import com.example.booksshop.entity.BookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookDao extends JpaRepository<Book, BookId> {

    @Query("""
select new com.example.booksshop.dto.OrderItemInfo(o.id, o.totalAmount, ot.books, o.orderDate)
from Customer  c join c.orders o join o.orderItems ot where c.customerName = ?1
""")
    public OrderItemInfo fetchOrderItemInfo(String userName);
}
